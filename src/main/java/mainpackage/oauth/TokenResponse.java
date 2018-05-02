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
  private String expires_in;

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

  /**
   * The value of the scope parameter is expressed as a list of space- delimited,
   * case-sensitive strings. The strings are defined by the authorization server.
   * If the value contains multiple space-delimited strings, their order does not
   * matter, and each string adds an additional access range to the requested
   * scope.
   * 
   * scope = scope-token * (SP scope-token) scope-token = 1*( %x21 / %x23-5B /
   * %x5D-7E )
   */
  @JsonProperty
  private String scope;

  /**
   * if the "state" parameter was present in the client authorization request. The
   * exact value received from the client.
   */
  @JsonProperty
  private String state;

}