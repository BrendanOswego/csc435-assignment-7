package mainpackage.db;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "author")
public class Author {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  @JsonProperty
  private UUID id;

  @JsonProperty
  private String first_name;

  @JsonProperty
  private String last_name;

  public Author() {
  }

  public Author(UUID id, String first_name, String last_name) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
  }

  public Author(String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
  }

  public void setID(UUID id) {
    this.id = id;
  }

  public void setFirstName(String first_name) {
    this.first_name = first_name;
  }

  public void setLastName(String last_name) {
    this.last_name = last_name;
  }

  @JsonIgnore
  public UUID getID() {
    return id;
  }

  @JsonIgnore
  public String getFirstName() {
    return first_name;
  }

  @JsonIgnore
  public String getLastName() {
    return last_name;
  }

}