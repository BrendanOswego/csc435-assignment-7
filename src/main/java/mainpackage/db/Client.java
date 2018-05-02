package mainpackage.db;

import javax.persistence.Entity;

@Entity
public class Client {

  private String app_name;

  private String client_id;

  private String client_secret;

  private String homepage;

  public Client(String client_id, String client_secret, String app_name, String homepage) {
    this.client_id = client_id;
    this.app_name = app_name;
    this.client_secret = client_id;
    this.homepage = homepage;
  }

  public String getClientId() {
    return client_id;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getAppName() {
    return app_name;
  }

  public String getClientSecret() {
    return client_secret;
  }

}