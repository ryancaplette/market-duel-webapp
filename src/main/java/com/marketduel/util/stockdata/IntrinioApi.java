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
import java.util.ArrayList;
import java.util.Base64;

/**
 * Created by ryan on 5/29/2017.
 */
public class IntrinioApi {

    public IntrinioApi () {

    }

    public StockDataItem[] getStockData (String tickerSymbol) {

        StockDataItem[] stockDataItems = null;
        try {
            String[] data = null;
            String stockDataJsonResponse = this.requestStockData(tickerSymbol);
            stockDataItems = this.parseStockDataJsonResponse(stockDataJsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stockDataItems;
    }

    private String requestStockData (String tickerSymbol) throws IOException {

        String intrinioApi = "https://api.intrinio.com/data_point?identifier=" + tickerSymbol + "&item=open_price,high_price,low_price,close_price,last_price,dividend_ex_date,dividend_pay_date,dividend,trailing_dividend_yield,business_address,business_phone_no,ceo,name,company_url,country,employees,stock_exchange,ticker,52_week_high,52_week_low,change,volume,average_daily_volume";
        String username = "150a83d48e4cca79c2974f5f12455689";
        String password = "a5bda7628011d95e8a1ce087e6dc99f2";

        String authString = username + ":" + password;
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        String authStringEnc = new String(authEncBytes);

        URL url = new URL(intrinioApi);
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

    private StockDataItem[] parseStockDataJsonResponse (String stockDataJsonResponse) {
        ArrayList<StockDataItem> stockDataItems = new ArrayList<StockDataItem>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(stockDataJsonResponse);
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


}
