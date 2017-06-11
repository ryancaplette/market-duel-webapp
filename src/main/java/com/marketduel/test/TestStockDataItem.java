package com.marketduel.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.marketduel.util.stockdata.StockDataItem;

public class TestStockDataItem {

	@Test
	public void testGetIdentifier() {
		StockDataItem i = new StockDataItem("aapl", "last_price", "10.50");
		assertTrue (i.getIdentifier().equalsIgnoreCase("aapl"));
	}

	@Test
	public void testSetIdentifier() {
		StockDataItem i = new StockDataItem("aapl", "last_price", "10.50");
		i.setIdentifier("goog");
		assertTrue (i.getIdentifier().equalsIgnoreCase("goog"));
	}

	@Test
	public void testGetItem() {
		StockDataItem i = new StockDataItem("aapl", "last_price", "10.50");
		assertTrue (i.getItem().equalsIgnoreCase("last_price"));
	}

	@Test
	public void testSetItem() {
		StockDataItem i = new StockDataItem("aapl", "last_price", "10.50");
		i.setItem("close_price");
		assertTrue (i.getItem().equalsIgnoreCase("close_price"));
	}

	@Test
	public void testGetValue() {
		StockDataItem i = new StockDataItem("aapl", "last_price", "10.50");
		assertTrue (i.getValue().equalsIgnoreCase("10.50"));
	}

	@Test
	public void testSetValue() {
		StockDataItem i = new StockDataItem("aapl", "last_price", "10.50");
		i.setValue("22.30");
		assertTrue (i.getValue().equalsIgnoreCase("22.30"));
	}

}
