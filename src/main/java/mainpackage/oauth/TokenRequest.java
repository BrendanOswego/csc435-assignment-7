package mainpackage.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenRequest {

  @JsonProperty
  private String username;

  @JsonProperty
  private String password;

  /**
   * Value MUST be set to "password".
   */
  @JsonProperty
  private String grant_type = "password";

}