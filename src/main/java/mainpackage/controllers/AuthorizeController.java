package mainpackage.controllers;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.server.Uri;
import org.jdbi.v3.core.Jdbi;

import mainpackage.dao.RegisterDAO;
import mainpackage.db.Register;
import mainpackage.helpers.Credentials;

@Path("/oauth2/authorize")
public class AuthorizeController {

  private RegisterDAO registerDAO;

  public AuthorizeController(Jdbi database) {
    this.registerDAO = database.onDemand(RegisterDAO.class);
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

    URI uri = UriBuilder.fromUri(redirect_uri + "?code=" + code).build();
    return Response.status(Response.Status.FOUND).location(uri).build();
  }

}