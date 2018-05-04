package mainpackage.controllers;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import mainpackage.oauth.OAuthRoles;

@Path("/users")
public class UserController {

  @GET
  @RolesAllowed(OAuthRoles.READ_ONLY)
  public Response index(@Auth String token) {
    return Response.ok().build();
  }

}