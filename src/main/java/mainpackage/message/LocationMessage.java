package mainpackage.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationMessage {

  @JsonProperty
  private String message;

  @JsonProperty
  private final String location;

  public LocationMessage(String message, String location) {
    this.message = message;
    this.location = location;
  }
}