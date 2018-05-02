package mainpackage.controllers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mainpackage.oauth.TokenRequest;

@Path("/token-request")
@Produces(MediaType.APPLICATION_JSON)
public class TokenController {

  @POST
  public Response authorize(TokenRequest req) {
    return Response.ok(req).build();
  }

}