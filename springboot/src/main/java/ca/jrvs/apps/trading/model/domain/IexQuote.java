package ca.jrvs.apps.trading.model.domain;

public class IexQuote {
    private String symbol,id,key,subkey,date,label;
    private Long openTime,volume,updated,marketeChangeOverTime,fVolume,change,changePercent;
    private double high,close,low,open,uOpen,uHigh,uClose,uLow,uVolume,fOpen,fClose,fHigh,fLow;

    public IexQuote(String symbol, String id, String key) {
        this.symbol = symbol;
        this.id = id;
        this.key = key;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSubkey() {
        return subkey;
    }

    public void setSubkey(String subkey) {
        this.subkey = subkey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Double getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Long getMarketeChangeOverTime() {
        return marketeChangeOverTime;
    }

    public void setMarketeChangeOverTime(Long marketeChangeOverTime) {
        this.marketeChangeOverTime = marketeChangeOverTime;
    }

    public Long getfVolume() {
        return fVolume;
    }

    public void setfVolume(Long fVolume) {
        this.fVolume = fVolume;
    }

    public Long getChange() {
        return change;
    }

    public void setChange(Long change) {
        this.change = change;
    }

    public Long getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(Long changePercent) {
        this.changePercent = changePercent;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getuOpen() {
        return uOpen;
    }

    public void setuOpen(double uOpen) {
        this.uOpen = uOpen;
    }

    public double getuHigh() {
        return uHigh;
    }

    public void setuHigh(double uHigh) {
        this.uHigh = uHigh;
    }

    public double getuClose() {
        return uClose;
    }

    public void setuClose(double uClose) {
        this.uClose = uClose;
    }

    public double getuLow() {
        return uLow;
    }

    public void setuLow(double uLow) {
        this.uLow = uLow;
    }

    public double getuVolume() {
        return uVolume;
    }

    public void setuVolume(double uVolume) {
        this.uVolume = uVolume;
    }

    public double getfOpen() {
        return fOpen;
    }

    public void setfOpen(double fOpen) {
        this.fOpen = fOpen;
    }

    public double getfClose() {
        return fClose;
    }

    public void setfClose(double fClose) {
        this.fClose = fClose;
    }

    public double getfHigh() {
        return fHigh;
    }

    public void setfHigh(double fHigh) {
        this.fHigh = fHigh;
    }

    public double getfLow() {
        return fLow;
    }

    public void setfLow(double fLow) {
        this.fLow = fLow;
    }


}
