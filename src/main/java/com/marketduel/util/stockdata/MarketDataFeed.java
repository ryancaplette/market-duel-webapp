package com.marketduel.util.stockdata;

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
        Stock stock = null;
        try {
            stock = new Stock();
            stock.setDataPointItems(api.getCompanyInfo(tickerSymbol));
            stock.updatePriceDataHistory(api.getStockPriceDataToday(tickerSymbol));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return stock;
    }
}
