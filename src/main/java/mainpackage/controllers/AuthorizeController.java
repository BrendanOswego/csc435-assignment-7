package mainpackage.controllers;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jdbi.v3.core.Jdbi;

import mainpackage.dao.AuthorizeDAO;
import mainpackage.dao.RegisterDAO;
import mainpackage.db.Authorization;
import mainpackage.db.Register;
import mainpackage.helpers.Credentials;

@Path("/oauth2/authorize")
public class AuthorizeController {

  private RegisterDAO registerDAO;
  private AuthorizeDAO authorizeDAO;

  public AuthorizeController(Jdbi database) {
    registerDAO = database.onDemand(RegisterDAO.class);
    authorizeDAO = database.onDemand(AuthorizeDAO.class);
  }

  @GET
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_FORM_URLENCODED)
  public Response request(@QueryParam("response_type") String response_type, @QueryParam("client_id") String client_id,
      @QueryParam("redirect_uri") String redirect_uri, @QueryParam("scope") String scope,
      @QueryParam("state") String state) {

    Register client = registerDAO.get(client_id);
    if (client == null)
      return Response.status(Response.Status.NOT_FOUND).build();

    String code = Credentials.instance().authorizationCode(client_id);

    authorizeDAO.insert(client_id, code, redirect_uri != null ? redirect_uri : "");

    Authorization temp = authorizeDAO.get(client_id);
    if (temp == null)
      return Response.status(Response.Status.CONFLICT).build();

    URI uri = UriBuilder.fromUri(redirect_uri + "?code=" + code).build();
    return Response.status(Response.Status.FOUND).location(uri).build();
  }

  @GET
  @Path("{client_id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCode(@PathParam("client_id") String client_id) {
    Authorization temp = authorizeDAO.get(client_id);

    if (temp == null)
      return Response.status(Response.Status.NOT_FOUND).build();

    return Response.ok().entity(temp).build();
  }

}