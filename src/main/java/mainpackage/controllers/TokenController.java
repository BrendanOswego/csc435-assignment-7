package mainpackage.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;

import mainpackage.dao.AuthorizationDAO;
import mainpackage.dao.ClientDAO;
import mainpackage.dao.TokenDAO;
import mainpackage.db.Authorization;
import mainpackage.db.Client;
import mainpackage.db.Token;
import mainpackage.helpers.Credentials;
import mainpackage.oauth.TokenRequest;
import mainpackage.oauth.TokenResponse;
import mainpackage.views.TokenView;

@Path("/token")
public class TokenController {

  private TokenDAO tokenDAO;
  private ClientDAO clientDAO;
  private AuthorizationDAO authorizeDAO;

  public TokenController(Jdbi database) {
    tokenDAO = database.onDemand(TokenDAO.class);
    clientDAO = database.onDemand(ClientDAO.class);
    authorizeDAO = database.onDemand(AuthorizationDAO.class);
  }

  @GET
  public TokenView index() {
    return new TokenView();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response authorize(TokenRequest req) {
    if (req.getClientId() == null || req.getClientSecret() == null || req.getCode() == null
        || req.getRedirectUri() == null)
      return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    Client client = clientDAO.get(req.getClientId());
    Authorization authorize = authorizeDAO.get(req.getClientId());

    if (client == null || (!authorize.getCode().equals(req.getCode())))
      return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    // NOW YOU CAN DO FUN STUFF
    String access_token = Credentials.instance().accessToken(req.getClientId(), req.getCode(), req.getRedirectUri());
    String refresh_token = Credentials.instance().refreshToken(req.getClientSecret(), req.getCode(),
        req.getRedirectUri());

    int expires_in = 3600;

    tokenDAO.insert(req.getClientId(), access_token, refresh_token, expires_in);

    Token token = tokenDAO.get(req.getClientId());

    return Response.ok(token).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response authorize(@FormParam("code") String code, @FormParam("client-id") String client_id,
      @FormParam("client-secret") String client_secret, @FormParam("redirect-uri") String redirect_uri) {
    if (code == null || client_id == null || client_secret == null || redirect_uri == null)
      return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    Client client = clientDAO.get(client_id);
    Authorization authorize = authorizeDAO.get(client_id);

    if (client == null || (!authorize.getCode().equals(code)))
      return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    // NOW YOU CAN DO FUN STUFF

    String access_token = Credentials.instance().accessToken(client_id, code, redirect_uri);
    String refresh_token = Credentials.instance().refreshToken(client_secret, code, redirect_uri);

    int expires_in = 3600;

    tokenDAO.insert(client_id, access_token, refresh_token, expires_in);

    Token token = tokenDAO.get(client_id);

    return Response.ok(token).build();
  }

}