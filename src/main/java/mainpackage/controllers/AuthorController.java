package mainpackage.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


import mainpackage.dao.AuthorDAO;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorController {

  private final AuthorDAO dao;

  public AuthorController(AuthorDAO dao) {
    this.dao = dao;
  }

  @GET
  public Response index() {
    return Response.ok("authors").build();
  }

}