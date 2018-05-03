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
import mainpackage.db.Authorization;
import mainpackage.db.Client;
import mainpackage.helpers.Credentials;
import mainpackage.oauth.AuthorizationRequest;
import mainpackage.views.AuthorizeView;

@Path("/authorize")
public class AuthorizeController {

  private ClientDAO clientDAO;
  private AuthorizationDAO authorizeDAO;

  public AuthorizeController(Jdbi database) {
    clientDAO = database.onDemand(ClientDAO.class);
    authorizeDAO = database.onDemand(AuthorizationDAO.class);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public AuthorizeView index() {
    return new AuthorizeView();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response authorize(AuthorizationRequest req) {
    if (req.getClientId() == null || req.getRedirectUri() == null)
      return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    Client client = clientDAO.get(req.getClientId());
    if (client == null)
      return Response.status(Response.Status.NOT_FOUND).build();

    // add to authorization table
    String code = Credentials.instance().authorizationCode(client.getClientId());
    authorizeDAO.insert(client.getClientId(), code, req.getRedirectUri());
    Authorization temp = authorizeDAO.get(client.getClientId());
    if (temp != null)
      return Response.status(Response.Status.CREATED).entity(temp).build();

    return Response.status(Response.Status.CONFLICT).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response authorize(@FormParam("client-id") String client_id, @FormParam("redirect-uri") String redirect_uri) {
    if (client_id == null || redirect_uri == null)
      return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    Client client = clientDAO.get(client_id);
    if (client == null)
      return Response.status(Response.Status.NOT_FOUND).build();

    // add to authorization table
    String code = Credentials.instance().authorizationCode(client.getClientId());
    authorizeDAO.insert(client.getClientId(), code, redirect_uri);
    Authorization temp = authorizeDAO.get(client.getClientId());
    if (temp != null)
      return Response.status(Response.Status.CREATED).entity(temp).build();

    return Response.status(Response.Status.CONFLICT).build();
  }

}