package mainpackage.db;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Register {

  @JsonProperty
  private String client_id;

  @JsonProperty
  private String client_secret;

  private Register() {
  }

  public Register(String client_id, String client_secret) {
    this.client_id = client_id;
    this.client_secret = client_secret;
  }

  @JsonIgnore
  public String getClientId() {
    return client_id;
  }

  @JsonIgnore
  public String getClientSecret() {
    return client_secret;
  }

}