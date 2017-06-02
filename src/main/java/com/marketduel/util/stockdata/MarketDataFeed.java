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
        StockDataItem[] stockDataItems = api.getStockData(tickerSymbol);

        if (stockDataItems.length == 0) {
            return null;
        }

        Stock stock = null;
        try {
            stock = new Stock(stockDataItems);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stock;
    }
}
