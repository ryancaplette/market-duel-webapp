package com.marketduel.util.stockdata;

import java.util.HashMap;

/**
 * Created by ryan on 5/28/2017.
 *
 * A class for requesting market data
 */
public class MarketDataFeed {

    public MarketDataFeed () {

    }

    public Stock requestStockByTickerSymbol (String tickerSymbol) {

        tickerSymbol = tickerSymbol.toUpperCase();

        IntrinioApi api = new IntrinioApi();

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

            HashMap<String, StockPriceData> stockPriceData = api.getStockPriceDataToday(tickerSymbol);
            stock.updatePriceDataHistory(stockPriceData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (doesCompanyExist) {
            return stock;
        }

        return null;
    }
}
