package com.marketduel.util.stockdata;

/**
 * Created by ryan on 6/15/2017.
 */
public class StockPriceData {

    private String date = "";
    private double open = 0;
    private double high = 0;
    private double low = 0;
    private double volume = 0;
    private double close = 0;

    public StockPriceData () {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = Math.round(open*100.0)/100.0;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = Math.round(high*100.0)/100.0;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = Math.round(low*100.0)/100.0;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = Math.round(close*100.0)/100.0;
    }
}
