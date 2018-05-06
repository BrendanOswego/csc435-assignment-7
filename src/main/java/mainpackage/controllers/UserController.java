package mainpackage.controllers;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.Auth;
import mainpackage.oauth.OAuthRoles;
import mainpackage.oauth.OAuthUser;

@Path("/users")
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  public UserController(Jdbi database) {

  }

  @GET
  @RolesAllowed(OAuthRoles.READ_ONLY)
  public Response index(@Auth OAuthUser user) {
    return Response.ok("Gained access to users").build();
  }

}