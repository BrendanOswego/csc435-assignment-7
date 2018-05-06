package mainpackage.controllers;

import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/oauth2/callback")
public class CallbackController {

  @GET
  public Response index(@Encoded @QueryParam("code") String code) {
    return Response.ok().build();
  }

}