package mainpackage.db;

import javax.persistence.Entity;

@Entity
public class Token {

  private String client_id;

  private String access_token;

  private String refresh_token;

  private int expires_in;

  public Token(String client_id, String access_token, String refresh_token, int expires_in) {
    this.client_id = client_id;
    this.access_token = access_token;
    this.refresh_token = refresh_token;
    this.expires_in = expires_in;
  }

  public String getClientId() {
    return client_id;
  }

  public String getAccessToken() {
    return access_token;
  }

  public String getRefreshToken() {
    return refresh_token;
  }

  public int getExiresIn() {
    return expires_in;
  }

}