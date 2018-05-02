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

import mainpackage.dao.ClientDAO;
import mainpackage.db.Client;
import mainpackage.helpers.Credentials;
import mainpackage.views.RegisterView;

@Path("/register")
@Produces(MediaType.TEXT_HTML)
public class RegistrationController {

  private ClientDAO clientDAO;

  public RegistrationController(Jdbi database) {
    clientDAO = database.onDemand(ClientDAO.class);
  }

  @GET
  public RegisterView register() {
    return new RegisterView(false);
  }

  /**
   * handles the POST request for registering an application to the server, and
   * generates a corresponding client_id.
   * 
   * Also stores the client_id and homepage in DB
   */
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public RegisterView registered(@FormParam("app-name") String name, @FormParam("app-url") String url) {
    String clientId = Credentials.instance().clientId(name, url);
    String clientSecret = Credentials.instance().clientSecret(clientId, name, url);
    clientDAO.insert(clientId, clientSecret, name, url);
    return new RegisterView(true, clientId, clientSecret);
  }

}