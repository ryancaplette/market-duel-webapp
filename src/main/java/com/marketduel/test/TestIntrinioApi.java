package com.marketduel.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.marketduel.util.stockdata.IntrinioApi;
import com.marketduel.util.stockdata.StockDataItem;

public class TestIntrinioApi {

	@Test
	public void testGetStockData() {
		IntrinioApi api = new IntrinioApi();
		StockDataItem[] stockDataItems = api.getStockData("aapl");
		assertTrue (stockDataItems.length > 0);
		assertTrue (stockDataItems[0].getIdentifier().equalsIgnoreCase("aapl"));
	}

}
