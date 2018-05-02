package mainpackage.controllers;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;

import mainpackage.dao.UserDAO;
import mainpackage.db.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

  private UserDAO userDAO;

  public UserController(Jdbi database) {
    this.userDAO = database.onDemand(UserDAO.class);
  }

  @GET
  public Response index() {
    return Response.ok(userDAO.get()).build();
  }

  @POST
  public Response addUser(User user) {
    UUID id = userDAO.post(user.getUsername(), user.getPassword());

    if (id == null)
      return Response.status(Response.Status.BAD_REQUEST).entity("\"could not create user\"").build();

    User temp = userDAO.get(id);

    return Response.status(Response.Status.CREATED).entity(temp).build();
  }

}