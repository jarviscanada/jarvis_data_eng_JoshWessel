package ca.jrvs.apps.trading.model.domain;

public class IexQuote {

  private Double iexAskPrice;
  private Integer iexAskSize;
  private Double iexBidPrice;
  private Integer iexBidSize;
  private Double latestPrice;
  private String symbol;

  public Double getIexAskPrice() {
    return iexAskPrice;
  }

  public void setIexAskPrice(Double iexAskPrice) {
    this.iexAskPrice = iexAskPrice;
  }

  public Integer getIexAskSize() {
    return iexAskSize;
  }

  public void setIexAskSize(Integer iexAskSize) {
    this.iexAskSize = iexAskSize;
  }

  public Double getIexBidPrice() {
    return iexBidPrice;
  }

  public void setIexBidPrice(Double iexBidPrice) {
    this.iexBidPrice = iexBidPrice;
  }

  public Integer getIexBidSize() {
    return iexBidSize;
  }

  public void setIexBidSize(Integer iexBidSize) {
    this.iexBidSize = iexBidSize;
  }

  public Double getLatestPrice() {
    return latestPrice;
  }

  public void setLatestPrice(Double latestPrice) {
    this.latestPrice = latestPrice;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}