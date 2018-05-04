package mainpackage.controllers;

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

import org.jboss.logging.FormatWith;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mainpackage.dao.RegisterDAO;
import mainpackage.dao.TokenDAO;
import mainpackage.db.Register;
import mainpackage.helpers.Credentials;
import mainpackage.oauth.Token;

@Path("/oauth2/token")
public class TokenController {

  Logger logger = LoggerFactory.getLogger(TokenController.class);

  private TokenDAO tokenDAO;
  private RegisterDAO registerDAO;

  public TokenController(Jdbi database) {
    tokenDAO = database.onDemand(TokenDAO.class);
    registerDAO = database.onDemand(RegisterDAO.class);
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response post(@FormParam("grant_type") String grant_type, @FormParam("code") String code,
      @FormParam("redirect_uri") String redirect_uri, @FormParam("client_id") String client_id) {

    Register client = registerDAO.get(client_id);
    if (client == null) {
      return Response.status(Response.Status.NO_CONTENT).build();
    }

    String access_token = Credentials.instance().accessToken(client_id, code, redirect_uri);
    String refresh_token = Credentials.instance().refreshToken(client.getClientSecret(), code, redirect_uri);

    int expires_in = 3600;

    String token_type = "Bearer";

    tokenDAO.insert(access_token, token_type, expires_in, refresh_token, client_id);

    Token token = tokenDAO.get(access_token);
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

    return Response.status(Response.Status.NO_CONTENT).build();
  }

}