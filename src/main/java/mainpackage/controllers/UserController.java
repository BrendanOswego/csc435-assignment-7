package mainpackage.controllers;

import java.time.Instant;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.Auth;
import mainpackage.dao.TokenDAO;
import mainpackage.oauth.OAuthRoles;
import mainpackage.oauth.OAuthUser;
import mainpackage.oauth.Token;

@Path("/users")
public class UserController {
  private Logger logger = LoggerFactory.getLogger(UserController.class);

  private TokenDAO tokenDAO;

  public UserController(Jdbi database) {
    tokenDAO = database.onDemand(TokenDAO.class);
  }

  @GET
  @RolesAllowed(OAuthRoles.READ_ONLY)
  public Response index(@Auth OAuthUser user) {
    return Response.ok("Gained access to users").build();
  }

}