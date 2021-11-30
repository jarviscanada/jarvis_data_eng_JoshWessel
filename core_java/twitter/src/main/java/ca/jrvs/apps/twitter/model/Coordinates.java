package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Coordinates {

  @JsonProperty("coordinates")
  private List<Float> coordinates;
  @JsonProperty("type")
  private String type;

  @JsonProperty("coordinates")
  public List<Float> getCoordinates() {
    return coordinates;
  }

  @JsonProperty("coordinates")
  public void setCoordinates(List<Float> coordinates) {
    this.coordinates = coordinates;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }
}
