package mainpackage.views;

import io.dropwizard.views.View;

public class TokenView extends View {
  public TokenView() {
    super("token.mustache");
  }
}