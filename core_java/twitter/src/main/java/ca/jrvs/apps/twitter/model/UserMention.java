package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMention {

  @JsonProperty("indices")
  private int[] indices;
  @JsonProperty("text")
  private String text;

  @JsonProperty("indices")
  public int[] getIndices() {
    return indices;
  }

  @JsonProperty("indices")
  public void setIndices(int[] indices) {
    this.indices = indices;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }
}
