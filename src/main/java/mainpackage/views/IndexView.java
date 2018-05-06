package mainpackage.views;

import io.dropwizard.views.View;
import mainpackage.oauth.GoogleOAuth;

public class IndexView extends View {

  private String googleContent = GoogleOAuth.CLIENT_ID;

  public IndexView() {
    super("index.mustache");
  }

  public String getGoogleContent() {
    return googleContent;
  }

}