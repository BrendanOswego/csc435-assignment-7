package mainpackage.db;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
public class Authorization {

  @JsonProperty
  private String client_id;

  @JsonProperty
  private String code;

  @JsonProperty
  private String redirect_uri;

  public Authorization(String client_id, String code, String redirect_uri) {
    this.client_id = client_id;
    this.code = code;
    this.redirect_uri = redirect_uri;
  }

  @JsonIgnore
  public String getClientId() {
    return client_id;
  }

  @JsonIgnore
  public String getCode() {
    return code;
  }

  @JsonIgnore
  public String getRedirectUri() {
    return redirect_uri;
  }

}