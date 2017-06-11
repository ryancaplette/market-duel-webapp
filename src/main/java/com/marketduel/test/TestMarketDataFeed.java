package com.marketduel.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.marketduel.util.stockdata.MarketDataFeed;
import com.marketduel.util.stockdata.Stock;

public class TestMarketDataFeed {

	@Test
	public void testRequestStockByTickerSymbol() {
		MarketDataFeed mdf = new MarketDataFeed();
		Stock stock = mdf.requestStockByTickerSymbol("aapl");
		assertNotNull (stock);
	}

}
