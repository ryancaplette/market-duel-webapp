package com.marketduel.util.stockdata;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ryan on 5/29/2017.
 */
public class IntrinioApi {

	private String username = "150a83d48e4cca79c2974f5f12455689";
	private String password = "796215f1fddeaa2fe6dd424f2660368c";
	
    public IntrinioApi () {

    }

    public StockDataItem[] getCompanyInfo(String tickerSymbol) {

        StockDataItem[] stockDataItems = null;
        try {
            String companyInfoJsonResponse = this.requestCompanyInfo(tickerSymbol);
            stockDataItems = this.parseCompanyInfoJsonResponse(companyInfoJsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stockDataItems;
    }

    public StockDataItem[] getStockDataPoints(String tickerSymbol) {

        StockDataItem[] stockDataItems = null;
        try {
            String stockDataJsonResponse = this.requestStockDataPoints(tickerSymbol);
            stockDataItems = this.parseStockDataPointsJsonResponse(stockDataJsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stockDataItems;
    }

    public HashMap<String, StockPriceData> getStockPriceDataToday(String tickerSymbol) {

        try {
            String stockPriceDataJsonResponse = this.requestStockPriceDataToday(tickerSymbol);
            HashMap<String, StockPriceData> stockPriceData = this.parseStockPriceDataJsonResponse(stockPriceDataJsonResponse);

            return stockPriceData;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public StockPriceHistory getStockPriceHistory(String tickerSymbol, String startDate, String endDate, String frequency) {
//
//        try {
//            String[] data = null;
//            String stockPriceHistoryJsonResponse = this.requestStockPriceHistory(tickerSymbol, startDate, endDate, frequency);
//            StockPriceHistory stockPriceHistory = this.parseStockPriceHistoryJsonResponse(stockPriceHistoryJsonResponse);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return stockPriceHistory;
//    }

    /**
     * Requests data by company api endpoint
     *
     * @param tickerSymbol
     * @return
     * @throws IOException
     */
    private String requestCompanyInfo(String tickerSymbol) throws IOException {
        String restfulCall = "https://api.intrinio.com/companies?identifier=" + tickerSymbol;
        String jsonResponse = requestApiCall(restfulCall);
        return jsonResponse;
    }

    /**
     * Requests additional data by the stock data points api endpoint
     *
     * @param tickerSymbol
     * @return
     * @throws IOException
     */
    private String requestStockDataPoints(String tickerSymbol) throws IOException {
        String restfulCall = "https://api.intrinio.com/data_point?identifier=" + tickerSymbol + "&item=open_price,high_price,low_price,close_price,last_price,dividend_ex_date,dividend_pay_date,dividend,trailing_dividend_yield,business_address,business_phone_no,ceo,name,company_url,country,employees,stock_exchange,ticker,52_week_high,52_week_low,change,volume,average_daily_volume";
        String jsonResponse = requestApiCall(restfulCall);
        return jsonResponse;
    }

    private String requestStockPriceDataToday(String tickerSymbol) throws IOException {

        String frequency = "daily";

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String todaysDate = dateFormat.format(date);

        return requestStockPriceHistory(tickerSymbol, todaysDate, todaysDate, frequency);
    }

    private String requestStockPriceHistory(String tickerSymbol, String startDate, String endDate, String frequency) throws IOException {
        String restfulCall = "https://api.intrinio.com/prices?identifier="
                + tickerSymbol
                + "&start_date=" + startDate
                + "&end_date=" + endDate
                + "&frequency=" + frequency;
        String jsonResponse = requestApiCall(restfulCall);
        return jsonResponse;
    }

    /**
     * Handles making restful get request api calls and authentication.
     *
     * @param apiRequest
     * @return String jsonResponse
     * @throws IOException
     */
    private String requestApiCall(String apiRequest) throws IOException {
        String authString = this.username + ":" + this.password;
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        String authStringEnc = new String(authEncBytes);

        URL url = new URL(apiRequest);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
        InputStream is = urlConnection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);

        int numCharsRead;
        char[] charArray = new char[1024];
        StringBuffer sb = new StringBuffer();
        while ((numCharsRead = isr.read(charArray)) > 0) {
            sb.append(charArray, 0, numCharsRead);
        }
        String jsonResponse = sb.toString();
        return jsonResponse;
    }

    private StockDataItem[] parseCompanyInfoJsonResponse(String companyInfoJsonResponse) {

        String[] dataPoints = { //list of data points to collect from json response
                "business_address",
                "business_phone_no",
                "ceo",
                "name",
                "company_url",
                "hq_country",
                "employees",
                "stock_exchange"
        };

        ArrayList<StockDataItem> stockDataItems = new ArrayList<StockDataItem>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(companyInfoJsonResponse);
            JSONObject jsonObject = (JSONObject) obj;

            String identifier = (String) jsonObject.get("ticker");

            if (identifier == null) { //ticker data not found
                return null;
            } else {
                stockDataItems.add(new StockDataItem(identifier, "ticker", identifier));
            }

            for (String dataPoint : dataPoints) {
                String value = String.valueOf(jsonObject.get(dataPoint));
                stockDataItems.add(new StockDataItem(identifier, dataPoint, value));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        StockDataItem[] stockDataItemArray = new StockDataItem[stockDataItems.size()];
        return stockDataItems.toArray(stockDataItemArray);
    }

    private StockDataItem[] parseStockDataPointsJsonResponse(String stockDataPointsJsonResponse) {
        ArrayList<StockDataItem> stockDataItems = new ArrayList<StockDataItem>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(stockDataPointsJsonResponse);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray data = (JSONArray) jsonObject.get("data");

            for (Object o: data) {
                JSONObject dataTag = (JSONObject) o;

                String identifier = (String) dataTag.get("identifier").toString();
                String item = (String) dataTag.get("item").toString();
                String value = (String) dataTag.get("value").toString();
                stockDataItems.add(new StockDataItem(identifier, item, value));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        StockDataItem[] stockDataItemArray = new StockDataItem[stockDataItems.size()];
        return stockDataItems.toArray(stockDataItemArray);
    }

    private HashMap<String, StockPriceData> parseStockPriceDataJsonResponse(String stockPriceDataJsonResponse) {
        HashMap<String, StockPriceData> stockPriceDataItems = new HashMap<String, StockPriceData>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(stockPriceDataJsonResponse);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray data = (JSONArray) jsonObject.get("data");

            for (Object o: data) {
                JSONObject priceData = (JSONObject) o;

                StockPriceData stockPriceData = new StockPriceData();

                String date = priceData.get("date").toString();
                if (date == null || date.equals("")) { //prevent hash mapping invalid data
                    continue;
                }

                stockPriceData.setDate(date);
                stockPriceData.setClose(Double.parseDouble(priceData.get("close").toString()));
                stockPriceData.setHigh(Double.parseDouble(priceData.get("high").toString()));
                stockPriceData.setLow(Double.parseDouble(priceData.get("low").toString()));
                stockPriceData.setOpen(Double.parseDouble(priceData.get("open").toString()));
                stockPriceData.setVolume(Double.parseDouble(priceData.get("volume").toString()));

                stockPriceDataItems.put(date, stockPriceData);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        StockPriceData[] stockPriceDataItemsArray = new StockPriceData[stockPriceDataItems.size()];
        return stockPriceDataItems;
    }

//    private StockDataItem[] parseStockPriceHistoryJsonResponse(String stockPriceHistoryJsonResponse) {
//        ArrayList<StockPrice> stockPrices = new ArrayList<StockDataItem>();
//        JSONParser parser = new JSONParser();
//        try {
//            Object obj = parser.parse(stockPriceHistoryJsonResponse);
//            JSONObject jsonObject = (JSONObject) obj;
//
//            JSONArray data = (JSONArray) jsonObject.get("data");
//
//            for (Object o: data) {
//                JSONObject dataTag = (JSONObject) o;
//
//                String identifier = (String) dataTag.get("identifier").toString();
//                String item = (String) dataTag.get("item").toString();
//                String value = (String) dataTag.get("value").toString();
//                stockDataItems.add(new StockDataItem(identifier, item, value));
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        StockDataItem[] stockDataItemArray = new StockDataItem[stockDataItems.size()];
//        return stockDataItems.toArray(stockDataItemArray);
//    }

}
