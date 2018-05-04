package mainpackage.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;

import mainpackage.dao.RegisterDAO;
import mainpackage.db.Register;
import mainpackage.helpers.Credentials;
import mainpackage.oauth.RegisterApplication;

@Path("/oauth2/register")
public class RegisterController {

  private RegisterDAO registerDAO;

  public RegisterController(Jdbi database) {
    registerDAO = database.onDemand(RegisterDAO.class);
  }

  @GET
  public Response index() {
    return Response.ok().build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response post(RegisterApplication req) {
    String clientId = Credentials.instance().clientId(req.getAppName(), req.getRedirectUri());
    String clientSecret = Credentials.instance().clientSecret(clientId, req.getAppName(), req.getRedirectUri());
    registerDAO.insert(clientId, clientSecret);

    Register temp = registerDAO.get(clientId);
    if (temp != null)
      return Response.status(Response.Status.CREATED).entity(temp).build();

    return Response.status(Response.Status.CONFLICT).build();
  }

}