package mainpackage.db;

public class Authorization {

  private String client_id;

  private String code;

  private String redirect_uri;

  public Authorization(String client_id, String code, String redirect_uri) {
    this.client_id = client_id;
    this.code = code;
    this.redirect_uri = redirect_uri;
  }

  public String getClientId() {
    return client_id;
  }

  public String getCode() {
    return code;
  }

  public String getRedirectUri() {
    return redirect_uri;
  }

}