package mainpackage.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationRequest {

  private String response_type = "token";

  @JsonProperty
  private String client_id;

  @JsonProperty
  private String redirect_uri;

  private AuthorizationRequest() {
  }

  public AuthorizationRequest(String client_id, String redirect_url) {
    this.client_id = client_id;
    this.redirect_uri = redirect_url;
  }

  public String getResponseType() {
    return response_type;
  }

  public String getClientId() {
    return client_id;
  }

  public String getRedirectUri() {
    return redirect_uri;
  }

}