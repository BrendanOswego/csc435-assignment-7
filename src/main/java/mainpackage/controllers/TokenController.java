package mainpackage.controllers;

import java.time.Instant;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mainpackage.dao.AuthorizeDAO;
import mainpackage.dao.RegisterDAO;
import mainpackage.dao.TokenDAO;
import mainpackage.db.Authorization;
import mainpackage.db.Register;
import mainpackage.helpers.Credentials;
import mainpackage.oauth.Token;

@Path("/oauth2/token")
public class TokenController {

  Logger logger = LoggerFactory.getLogger(TokenController.class);

  private TokenDAO tokenDAO;
  private RegisterDAO registerDAO;
  private AuthorizeDAO authorizeDAO;

  public TokenController(Jdbi database) {
    tokenDAO = database.onDemand(TokenDAO.class);
    registerDAO = database.onDemand(RegisterDAO.class);
    authorizeDAO = database.onDemand(AuthorizeDAO.class);
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response post(@FormParam("grant_type") String grant_type, @FormParam("code") String code,
      @FormParam("redirect_uri") String redirect_uri, @FormParam("client_id") String client_id,
      @FormParam("refresh_token") String refresh_token) {

    String access;
    String refresh;
    Register client = null;

    if (grant_type.equals("refresh_token")) {
      client = refreshToken(refresh_token);
      if (client == null) {
        return Response.status(Response.Status.NO_CONTENT).build();
      }
      Authorization authorization = authorizeDAO.get(client.getClientId());
      access = Credentials.instance().accessToken(client.getClientId(), authorization.getCode(),
          authorization.getRedirectUri());
      refresh = Credentials.instance().refreshToken(client.getClientSecret(), authorization.getCode(),
          authorization.getRedirectUri());
    } else {
      client = registerDAO.get(client_id);
      if (client == null) {
        return Response.status(Response.Status.NO_CONTENT).build();
      }
      access = Credentials.instance().accessToken(client.getClientId(), code, redirect_uri);
      refresh = Credentials.instance().refreshToken(client.getClientSecret(), code, redirect_uri);
    }

    int expires_in = 3600;

    String token_type = "Bearer";

    long issued = Instant.now().getEpochSecond();
    long expires = issued + expires_in;

    tokenDAO.insert(access, token_type, expires_in, refresh, client.getClientId(), issued, expires);

    Token token = tokenDAO.get(access);
    if (token == null)
      return Response.status(Response.Status.CONFLICT).build();

    return Response.ok().entity(token).build();
  }

  @DELETE
  @Path("{access_token}")
  public Response delete(@PathParam("access_token") String access_token) {
    Token token = tokenDAO.get(access_token);
    if (token == null)
      return Response.status(Response.Status.NOT_FOUND).build();

    tokenDAO.delete(access_token);

    token = tokenDAO.get(access_token);

    if (token == null)
      return Response.ok("token deleted").build();

    return Response.status(Response.Status.CONFLICT).build();
  }

  private Register refreshToken(String refresh) {
    List<Token> tokens = tokenDAO.get();
    for (Token t : tokens) {
      if (t.getRefreshToken().equals(refresh)) {
        tokenDAO.delete(t.getAccessToken());
        return registerDAO.get(t.getClientId());
      }
    }
    return null;
  }

}