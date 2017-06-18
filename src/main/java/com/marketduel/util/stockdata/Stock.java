package com.marketduel.util.stockdata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private Double change = null;
    private Double averageDailyVolume = null;
    private Double week52High = null;
    private Double week52Low = null;
    private String dividendExDate = null;
    private String dividendPayDate = null;
    private Double dividend = null;
    private Double trailingDividendYield = null;
    private HashMap<String, StockPriceData> stockPriceHistory = new HashMap<String, StockPriceData>();

    public Stock () {
    	
    }
    
    public Stock (StockDataItem[] stockDataItems) throws Exception {
        this.setDataPointItems(stockDataItems);
    }

    public void setDataPointItems(StockDataItem[] stockDataItems) throws Exception {

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
        this.setCountry(dataItems.get("hq_country"));
        this.setCompanyUrl(dataItems.get("company_url"));
        this.setBusinessAddress(dataItems.get("business_address"));
        this.setBusinessPhoneNumber(dataItems.get("business_phone_no"));
        this.setCeo(dataItems.get("ceo"));
        this.setEmployees(dataItems.get("employees"));
        this.setChange(dataItems.get("change"));
        this.setAverageDailyVolume(dataItems.get("average_daily_volume"));
        this.setWeek52High(dataItems.get("52_week_high"));
        this.setWeek52Low(dataItems.get("52_week_low"));
        this.setDividendExDate(dataItems.get("dividend_ex_date"));
        this.setDividendPayDate(dataItems.get("dividend_pay_date"));
        this.setDividend(dataItems.get("dividend"));
        this.setTrailingDividendYield(dataItems.get("trailing_dividend_yield"));
    }

    /**
     * For inserting multiple stock price data into price history
     *
     * @param HashMap<String, StockPriceData> stockPriceData
     */
    public void updatePriceDataHistory(HashMap<String, StockPriceData> stockPriceData) {
        this.stockPriceHistory.putAll(stockPriceData);
    }

    /**
     * For inserting one record of stock price data into price history
     *
     * @param StockPriceData stockPriceData
     */
    public void updatePriceDataHistory(StockPriceData stockPriceData) {
        this.stockPriceHistory.put(stockPriceData.getDate(), stockPriceData);
    }

    private Double parseDouble (String i) {
        try {
            return Double.parseDouble(i);
        } catch (Exception e) {
            return null;
        }
    }

    private int parseInt (String i) {
        try {
            return Integer.parseInt(i);
        } catch (Exception e) {
            return 0;
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
        this.employees = this.parseInt(employees);
    }

    public Double getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = this.parseDouble(change);
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

    public HashMap<String, StockPriceData> getStockPriceHistory() {
        return stockPriceHistory;
    }

    public StockPriceData getStockPriceHistory(String requestedDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StockPriceData stockPriceData = null;
        try {
            String date = dateFormat.format(dateFormat.parse(requestedDate));
            stockPriceData = this.stockPriceHistory.get(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stockPriceData;
    }

    public StockPriceData getStockPriceToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String todaysDate = dateFormat.format(date);

        return this.getStockPriceHistory(todaysDate);
    }

}
