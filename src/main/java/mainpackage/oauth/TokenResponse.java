package mainpackage.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {

  /**
   * The access token issued by the authorization server.
   */
  @JsonProperty
  private String access_token;

  /**
   * The lifetime in seconds of the access token. For example, the value "3600"
   * denotes that the access token will expire in one hour from the time the
   * response was generated. If omitted, the authorization server SHOULD provide
   * the expiration time via other means or document the default value.
   */
  @JsonProperty
  private int expires_in;

  /**
   * issued to the client by the authorization server and are used to obtain a new
   * access token when the current access token becomes invalid or expires, or to
   * obtain additional access tokens with identical or narrower scope
   */
  @JsonProperty
  private String refresh_token;

  /**
   * the "bearer" token type defined in [RFC6750] is utilized by simply including
   * the access token string in the request
   */
  @JsonProperty
  private String token_type = "bearer";

  public String getAccessToken() {
    return access_token;
  }

  public int getExpiresIn() {
    return expires_in;
  }

  public String getRefreshToken() {
    return refresh_token;
  }

}