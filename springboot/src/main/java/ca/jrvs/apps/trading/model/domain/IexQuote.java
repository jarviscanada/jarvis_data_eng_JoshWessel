package ca.jrvs.apps.trading.model.domain;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "iexAskPrice",
    "iexAskSize",
    "iexBidPrice",
    "iexBidSize",
    "latestPrice",
    "symbol"
})
@Generated("jsonschema2pojo")
public class IexQuote {

  @JsonProperty("iexAskPrice")
  private String iexAskPrice;
  @JsonProperty("iexAskSize")
  private String iexAskSize;
  @JsonProperty("iexBidPrice")
  private String iexBidPrice;
  @JsonProperty("iexBidSize")
  private String iexBidSize;
  @JsonProperty("latestPrice")
  private String latestPrice;
  @JsonProperty("symbol")
  private String symbol;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("iexAskPrice")
  public String getIexAskPrice() {
    return iexAskPrice;
  }

  @JsonProperty("iexAskPrice")
  public void setIexAskPrice(String iexAskPrice) {
    this.iexAskPrice = iexAskPrice;
  }

  @JsonProperty("iexAskSize")
  public String getIexAskSize() {
    return iexAskSize;
  }

  @JsonProperty("iexAskSize")
  public void setIexAskSize(String iexAskSize) {
    this.iexAskSize = iexAskSize;
  }

  @JsonProperty("iexBidPrice")
  public String getIexBidPrice() {
    return iexBidPrice;
  }

  @JsonProperty("iexBidPrice")
  public void setIexBidPrice(String iexBidPrice) {
    this.iexBidPrice = iexBidPrice;
  }

  @JsonProperty("iexBidSize")
  public String getIexBidSize() {
    return iexBidSize;
  }

  @JsonProperty("iexBidSize")
  public void setIexBidSize(String iexBidSize) {
    this.iexBidSize = iexBidSize;
  }

  @JsonProperty("latestPrice")
  public String getLatestPrice() {
    return latestPrice;
  }

  @JsonProperty("latestPrice")
  public void setLatestPrice(String latestPrice) {
    this.latestPrice = latestPrice;
  }

  @JsonProperty("symbol")
  public String getSymbol() {
    return symbol;
  }

  @JsonProperty("symbol")
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}