package mainpackage.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenRequest {

  @JsonProperty
  private String code;

  @JsonProperty
  private String redirect_uri;

  @JsonProperty
  private String client_id;

  @JsonProperty
  private String client_secret;

  @JsonProperty
  private String grant_type = "authorization_code";

  public TokenRequest(String code, String redirect_uri, String client_id, String client_secret) {
    this.code = code;
    this.redirect_uri = redirect_uri;
    this.client_id = client_id;
    this.client_secret = client_secret;
  }

  public String getCode() {
    return code;
  }

  public String getRedirectUri() {
    return redirect_uri;
  }

  public String getClientId() {
    return client_id;
  }

  public String getClientSecret() {
    return client_secret;
  }

}