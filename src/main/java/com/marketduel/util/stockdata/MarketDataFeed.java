package com.marketduel.util.stockdata;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by ryan on 5/28/2017.
 *
 * A class for requesting market data
 */
public class MarketDataFeed {

    private IntrinioApi api;

    public MarketDataFeed () {
        this.api = new IntrinioApi();
    }

    public Stock requestStockByTickerSymbol (String tickerSymbol) {

        tickerSymbol = tickerSymbol.toUpperCase();

// Stock data point api disabled due to excessive calls
//        StockDataItem[] stockDataItems = api.getStockDataPoints(tickerSymbol);
//        if (stockDataItems.length == 0) {
//            return null;
//        }
//
        boolean doesCompanyExist = false;
        Stock stock = null;
        try {
            stock = new Stock();

            StockDataItem[] companyInfo = api.getCompanyInfo(tickerSymbol);
            if (companyInfo != null && companyInfo.length > 0) {
                doesCompanyExist = true;
                stock.setDataPointItems(companyInfo);
            }


            StockPriceData stockPriceData = api.getStockPriceDataToday(tickerSymbol);
            stock.updatePriceDataHistory(stockPriceData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (doesCompanyExist) {
            return stock;
        }

        return null;
    }

    public StockPriceData requestStockPriceDataForDate(String tickerSymbol, Date date) {

        tickerSymbol = tickerSymbol.toUpperCase();
        return api.getStockPriceDataForDate(tickerSymbol, date);
    }

    /**
     * gets the most recent 100 market days worth of StockPriceData stored in a hashmap where key is formatted yyyy-mm-dd
     * @param tickerSymbol
     * @return
     */
    public HashMap<String, StockPriceData> requestStockPriceData(String tickerSymbol) {

        tickerSymbol = tickerSymbol.toUpperCase();
        return api.getStockPriceData(tickerSymbol);
    }

    /**
     * gets the last stock price data
     * @param tickerSymbol
     * @return
     */
    public StockPriceData requestStockPriceDataLast(String tickerSymbol) {

        tickerSymbol = tickerSymbol.toUpperCase();
        return api.getStockPriceDataLast(tickerSymbol);
    }

}
