package com.marketduel.util.stockdata;

import java.util.HashMap;

/**
 * Created by ryan on 5/28/2017.
 */
public class Stock {

    private String companyName = null;
    private String ticker = null;
    private String stockExchange = null;
    private String country = null;
    private String companyUrl = null;
    private String businessAddress = null;
    private String businessPhoneNumber = null;
    private String ceo = null;
    private int employees = 0;
    private Double openPrice = null;
    private Double highPrice = null;
    private Double lowPrice = null;
    private Double closePrice = null;
    private Double lastPrice = null;
    private Double change = null;
    private Double volume = null;
    private Double averageDailyVolume = null;
    private Double week52High = null;
    private Double week52Low = null;
    private String dividendExDate = null;
    private String dividendPayDate = null;
    private Double dividend = null;
    private Double trailingDividendYield = null;

    public Stock () {
    	
    }
    
    public Stock (StockDataItem[] stockDataItems) throws Exception {
        this.setData(stockDataItems);
    }

    private void setData(StockDataItem[] stockDataItems) throws Exception {

        String uniqueIdentifier = null;

        HashMap<String, String> dataItems = new HashMap<String, String>();
        for (StockDataItem item : stockDataItems) {
            if (uniqueIdentifier == null) {
                uniqueIdentifier = item.getIdentifier();
            } else {
                if (!uniqueIdentifier.equals(item.getIdentifier())) {
                    throw new Exception("Data set contains items for multiple stocks");
                }
            }

            dataItems.put(item.getItem(), item.getValue());
        }

        this.setCompanyName(dataItems.get("name"));
        this.setTicker(dataItems.get("ticker"));
        this.setStockExchange(dataItems.get("stock_exchange"));
        this.setCountry(dataItems.get("country"));
        this.setCompanyUrl(dataItems.get("company_url"));
        this.setBusinessAddress(dataItems.get("business_address"));
        this.setBusinessPhoneNumber(dataItems.get("business_phone_no"));
        this.setCeo(dataItems.get("ceo"));
        this.setEmployees(dataItems.get("employees"));
        this.setOpenPrice(dataItems.get("open_price"));
        this.setHighPrice(dataItems.get("high_price"));
        this.setLowPrice(dataItems.get("low_price"));
        this.setClosePrice(dataItems.get("close_price"));
        this.setLastPrice(dataItems.get("last_price"));
        this.setChange(dataItems.get("change"));
        this.setVolume(dataItems.get("volume"));
        this.setAverageDailyVolume(dataItems.get("average_daily_volume"));
        this.setWeek52High(dataItems.get("52_week_high"));
        this.setWeek52Low(dataItems.get("52_week_low"));
        this.setDividendExDate(dataItems.get("dividend_ex_date"));
        this.setDividendPayDate(dataItems.get("dividend_pay_date"));
        this.setDividend(dataItems.get("dividend"));
        this.setTrailingDividendYield(dataItems.get("trailing_dividend_yield"));
    }

    private Double parseDouble (String i) {
        try {
            return Double.parseDouble(i);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    public void setBusinessPhoneNumber(String businessPhoneNumber) {
        this.businessPhoneNumber = businessPhoneNumber;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = Integer.parseInt(employees);
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = this.parseDouble(openPrice);
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = this.parseDouble(highPrice);
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = this.parseDouble(lowPrice);
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = this.parseDouble(closePrice);
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = this.parseDouble(lastPrice);
    }

    public Double getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = this.parseDouble(change);
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = this.parseDouble(volume);
    }

    public Double getAverageDailyVolume() {
        return averageDailyVolume;
    }

    public void setAverageDailyVolume(String averageDailyVolume) {
        this.averageDailyVolume = this.parseDouble(averageDailyVolume);
    }

    public Double getWeek52High() {
        return week52High;
    }

    public void setWeek52High(String week52High) {
        this.week52High = this.parseDouble(week52High);
    }

    public Double getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(String week52Low) {
        this.week52Low = this.parseDouble(week52Low);
    }

    public String getDividendExDate() {
        return dividendExDate;
    }

    public void setDividendExDate(String dividendExDate) {
        this.dividendExDate = dividendExDate;
    }

    public String getDividendPayDate() {
        return dividendPayDate;
    }

    public void setDividendPayDate(String dividendPayDate) {
        this.dividendPayDate = dividendPayDate;
    }

    public Double getDividend() {
        return dividend;
    }

    public void setDividend(String dividend) {
        this.dividend = this.parseDouble(dividend);
    }

    public Double getTrailingDividendYield() {
        return trailingDividendYield;
    }

    public void setTrailingDividendYield(String trailingDividendYield) {
        this.trailingDividendYield = this.parseDouble(trailingDividendYield);
    }
}
