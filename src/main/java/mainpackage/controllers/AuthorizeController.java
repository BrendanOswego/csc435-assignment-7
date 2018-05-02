package mainpackage.controllers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;

import mainpackage.dao.ClientDAO;
import mainpackage.db.Client;
import mainpackage.oauth.AuthorizationRequest;

@Path("/authorize")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorizeController {

  private ClientDAO clientDAO;

  public AuthorizeController(Jdbi database) {
    clientDAO = database.onDemand(ClientDAO.class);
  }

  @POST
  public Response authorize(AuthorizationRequest req) {
    Client client = clientDAO.get(req.getClientId());
    if (client == null)
      return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    return Response.ok(req).build();
  }

}