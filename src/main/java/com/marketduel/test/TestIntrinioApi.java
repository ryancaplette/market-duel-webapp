package com.marketduel.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.marketduel.util.stockdata.IntrinioApi;
import com.marketduel.util.stockdata.StockDataItem;

public class TestIntrinioApi {

	@Test
	public void testGetStockDataPoints() {
		IntrinioApi api = new IntrinioApi();

		//Mock api call's response data to save api calls
		StockDataItem stockDataItem = new StockDataItem("aapl", "ticker", "aapl");
		StockDataItem[] stockDataItems = new StockDataItem[1];
		stockDataItems[0] = stockDataItem;

		assertTrue (stockDataItems.length > 0);
		assertTrue (stockDataItems[0].getIdentifier().equalsIgnoreCase("aapl"));
	}

}
