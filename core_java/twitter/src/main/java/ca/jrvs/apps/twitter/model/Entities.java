package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entities {

  @JsonProperty("hashtags")
  private Hashtag[] hashtags;
  @JsonProperty("urls")
  private String[] urls;
  @JsonProperty("user_mentions")
  private UserMention[] user_mentions;
  @JsonProperty("symbols")
  private String[] symbols;
  @JsonProperty("media")
  private String[] media;
  @JsonProperty("polls")
  private String[] polls;

  @JsonProperty("hashtags")
  public Hashtag[] getHashtags() {
    return hashtags;
  }

  @JsonProperty("hashtags")
  public void setHashtags(Hashtag[] hashtags) {
    this.hashtags = hashtags;
  }

  @JsonProperty("user_mentions")
  public UserMention[] getUser_mentions() {
    return user_mentions;
  }

  @JsonProperty("user_mentions")
  public void setUser_mentions(UserMention[] user_mentions) {
    this.user_mentions = user_mentions;
  }
}
