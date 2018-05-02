package mainpackage.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationRequest {

  private String response_type = "token";

  @JsonProperty
  private String client_id;

  @JsonProperty
  private String redirect_uri;

  @JsonProperty
  private String scope;

  @JsonProperty
  private String state;

  private AuthorizationRequest() {
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

  public String getScope() {
    return scope;
  }

  public String getState() {
    return state;
  }

}