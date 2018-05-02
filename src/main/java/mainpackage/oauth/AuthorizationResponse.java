package mainpackage.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationResponse {
  @JsonProperty
  private String code;

  @JsonProperty
  private String state;

  public AuthorizationResponse(String code, String state) {
    this.code = code;
    this.state = state;
  }

  public String getCode() {
    return code;
  }

  public String getState() {
    return state;
  }

}