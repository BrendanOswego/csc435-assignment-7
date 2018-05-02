package mainpackage.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mainpackage.views.IndexView;

@Path("")
@Produces(MediaType.TEXT_HTML)
public class IndexController {

  @GET
  public IndexView index() {
    return new IndexView();
  }

}