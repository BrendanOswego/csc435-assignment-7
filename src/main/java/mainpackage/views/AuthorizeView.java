package mainpackage.views;

import io.dropwizard.views.View;

public class AuthorizeView extends View {

  public AuthorizeView() {
    super("authorize.mustache");
  }

}