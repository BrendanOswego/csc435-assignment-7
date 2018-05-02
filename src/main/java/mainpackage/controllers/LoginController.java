package mainpackage.controllers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import mainpackage.db.User;

@Path("/login")
public class LoginController {

  @POST
  public void createUser(User user) {

  }
}