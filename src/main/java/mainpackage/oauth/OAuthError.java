package mainpackage.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthError {

  /**
   * static String from ErrorType
   */
  @JsonProperty
  private String error;

  @JsonProperty("description")
  private String error_description;

  @JsonProperty("uri")
  private String error_uri;

  

}