package mainpackage.views;

import io.dropwizard.views.View;

public class RegisterView extends View {

  private boolean submitted;
  private String clientId;
  private String clientSecret;

  public RegisterView(boolean submitted) {
    super("register.mustache");
    this.submitted = submitted;
  }

  public RegisterView(boolean submitted, String clientId, String clientSecret) {
    super("register.mustache");
    this.submitted = submitted;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  public boolean isSubmitted() {
    return submitted;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }
  

}