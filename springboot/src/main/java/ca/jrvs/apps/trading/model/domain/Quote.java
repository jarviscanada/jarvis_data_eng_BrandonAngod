package ca.jrvs.apps.trading.model.domain;

public class Quote implements Entity<String>{
    private String ticker;
    private Double lastPrice,bidPrice,askPrice;
    private Integer bidSize, askSize;

    public Quote(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String getID() {
        return this.ticker;
    }

    @Override
    public void setId(String s) {
        this.ticker = s;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    public Integer getBidSize() {
        return bidSize;
    }

    public void setBidSize(Integer bidSize) {
        this.bidSize = bidSize;
    }

    public Integer getAskSize() {
        return askSize;
    }

    public void setAskSize(Integer askSize) {
        this.askSize = askSize;
    }
}
