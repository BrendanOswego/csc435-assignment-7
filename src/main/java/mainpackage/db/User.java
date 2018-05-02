package mainpackage.db;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
public class User {

  @Id
  @JsonProperty
  private UUID id;

  @JsonProperty
  private String username;

  @JsonProperty
  private String password;

  private User() {
  }

  public User(UUID id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}