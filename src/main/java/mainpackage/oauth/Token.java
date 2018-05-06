package mainpackage.oauth;

import java.math.BigInteger;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
public class Token {

  @JsonProperty
  private String access_token;

  @JsonProperty
  private String token_type;

  @JsonProperty
  private int expires_in;

  @JsonProperty
  private String refresh_token;

  @JsonProperty
  private String client_id;

  @JsonIgnore
  private long expires;

  @JsonIgnore
  private long issued;

  private Token() {
  }

  public Token(String access_token, String token_type, int expires_in, String refresh_token, String client_id,
      long issued, long expires) {
    this.access_token = access_token;
    this.token_type = token_type;
    this.expires_in = expires_in;
    this.refresh_token = refresh_token;
    this.client_id = client_id;
    this.issued = issued;
    this.expires = expires;
  }

  @JsonIgnore
  public String getAccessToken() {
    return access_token;
  }

  @JsonIgnore
  public String getTokenType() {
    return token_type;
  }

  @JsonIgnore
  public int getExpiresIn() {
    return expires_in;
  }

  @JsonIgnore
  public String getRefreshToken() {
    return refresh_token;
  }

  @JsonIgnore
  public String getClientId() {
    return client_id;
  }

  @JsonIgnore
  public long getIssued() {
    return issued;
  }

  @JsonIgnore
  public long getExpires() {
    return expires;
  }

}