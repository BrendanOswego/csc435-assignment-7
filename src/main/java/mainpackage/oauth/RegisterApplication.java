package mainpackage.oauth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterApplication {

  @JsonProperty
  private String app_name;

  @JsonProperty
  private String redirect_uri;

  private RegisterApplication() {
  }

  public RegisterApplication(String app_name, String redirect_uri) {
    this.app_name = app_name;
    this.redirect_uri = redirect_uri;
  }

  @JsonIgnore
  public String getAppName() {
    return app_name;
  }

  @JsonIgnore
  public String getRedirectUri() {
    return redirect_uri;
  }

}