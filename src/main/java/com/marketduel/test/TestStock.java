package com.marketduel.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.marketduel.util.stockdata.Stock;

public class TestStock {

	private Stock stock;
	
	public TestStock () {
		this.stock = new Stock();
	}
	
	@Test
	public void testGetCompanyName() {
		this.stock.setCompanyName("Test Inc.");
		assertTrue (this.stock.getCompanyName().equals("Test Inc."));
	}

	@Test
	public void testSetCompanyName() {
		this.stock.setCompanyName("Set Company Inc.");
		assertTrue (this.stock.getCompanyName().equals("Set Company Inc."));
	}

	@Test
	public void testGetTicker() {
		this.stock.setTicker("AAPL");
		assertTrue (this.stock.getTicker().equals("AAPL"));
	}

	@Test
	public void testSetTicker() {
		this.stock.setTicker("AAPL");
		assertTrue (this.stock.getTicker().equals("AAPL"));
	}

	@Test
	public void testGetStockExchange() {
		this.stock.setStockExchange("NASDAQ");
		assertTrue (this.stock.getStockExchange().equals("NASDAQ"));
	}

	@Test
	public void testSetStockExchange() {
		this.stock.setStockExchange("NASDAQ");
		assertTrue (this.stock.getStockExchange().equals("NASDAQ"));
	}

	@Test
	public void testGetCountry() {
		this.stock.setCountry("USA");
		assertTrue (this.stock.getCountry().equals("USA"));
	}

	@Test
	public void testSetCountry() {
		this.stock.setCountry("USA");
		assertTrue (this.stock.getCountry().equals("USA"));
	}

	@Test
	public void testGetCompanyUrl() {
		this.stock.setCompanyUrl("www.company.net");
		assertTrue (this.stock.getCompanyUrl().equals("www.company.net"));
	}

	@Test
	public void testSetCompanyUrl() {
		this.stock.setCompanyUrl("www.company.net");
		assertTrue (this.stock.getCompanyUrl().equals("www.company.net"));
	}

	@Test
	public void testGetBusinessAddress() {
		this.stock.setBusinessAddress("123 test road");
		assertTrue (this.stock.getBusinessAddress().equals("123 test road"));
	}

	@Test
	public void testSetBusinessAddress() {
		this.stock.setBusinessAddress("123 test road");
		assertTrue (this.stock.getBusinessAddress().equals("123 test road"));
	}

	@Test
	public void testGetBusinessPhoneNumber() {
		this.stock.setBusinessPhoneNumber("1 800 888 8888");
		assertTrue (this.stock.getBusinessPhoneNumber().equals("1 800 888 8888"));
	}

	@Test
	public void testSetBusinessPhoneNumber() {
		this.stock.setBusinessPhoneNumber("1 800 888 8888");
		assertTrue (this.stock.getBusinessPhoneNumber().equals("1 800 888 8888"));
	}

	@Test
	public void testGetCeo() {
		this.stock.setCeo("Tim Cook");
		assertTrue (this.stock.getCeo().equals("Tim Cook"));
	}

	@Test
	public void testSetCeo() {
		this.stock.setCeo("Tim Cook");
		assertTrue (this.stock.getCeo().equals("Tim Cook"));
	}

	@Test
	public void testGetEmployees() {
		this.stock.setEmployees("101");
		assertTrue (this.stock.getEmployees() == 101);
	}

	@Test
	public void testSetEmployees() {
		this.stock.setEmployees("101");
		assertTrue (this.stock.getEmployees() == 101);
	}

	@Test
	public void testGetChange() {
		this.stock.setChange("0.07");
		assertTrue (this.stock.getChange() == 0.07);
	}

	@Test
	public void testSetChange() {
		this.stock.setChange("0.07");
		assertTrue (this.stock.getChange() == 0.07);
	}

	@Test
	public void testGetAverageDailyVolume() {
		this.stock.setAverageDailyVolume("100000.5");
		assertTrue (this.stock.getAverageDailyVolume() == 100000.5);
	}

	@Test
	public void testSetAverageDailyVolume() {
		this.stock.setAverageDailyVolume("100000.5");
		assertTrue (this.stock.getAverageDailyVolume() == 100000.5);
	}

	@Test
	public void testGetWeek52High() {
		this.stock.setWeek52High("10.66");
		assertTrue (this.stock.getWeek52High() == 10.66);
	}

	@Test
	public void testSetWeek52High() {
		this.stock.setWeek52High("10.66");
		assertTrue (this.stock.getWeek52High() == 10.66);
	}

	@Test
	public void testGetWeek52Low() {
		this.stock.setWeek52Low("9.66");
		assertTrue (this.stock.getWeek52Low() == 9.66);
	}

	@Test
	public void testSetWeek52Low() {
		this.stock.setWeek52Low("9.66");
		assertTrue (this.stock.getWeek52Low() == 9.66);
	}

	@Test
	public void testGetDividendExDate() {
		this.stock.setDividendExDate("2017-05-01");
		assertTrue (this.stock.getDividendExDate().equals("2017-05-01"));
	}

	@Test
	public void testSetDividendExDate() {
		this.stock.setDividendExDate("2017-05-01");
		assertTrue (this.stock.getDividendExDate().equals("2017-05-01"));
	}

	@Test
	public void testGetDividendPayDate() {
		this.stock.setDividendPayDate("2017-06-01");
		assertTrue (this.stock.getDividendPayDate().equals("2017-06-01"));
	}

	@Test
	public void testSetDividendPayDate() {
		this.stock.setDividendPayDate("2017-06-01");
		assertTrue (this.stock.getDividendPayDate().equals("2017-06-01"));
	}

	@Test
	public void testGetDividend() {
		this.stock.setDividend("1.06");
		assertTrue (this.stock.getDividend() == 1.06);
	}

	@Test
	public void testSetDividend() {
		this.stock.setDividend("1.06");
		assertTrue (this.stock.getDividend() == 1.06);
	}

	@Test
	public void testGetTrailingDividendYield() {
		this.stock.setTrailingDividendYield("0.02");
		assertTrue (this.stock.getTrailingDividendYield() == 0.02);
	}

	@Test
	public void testSetTrailingDividendYield() {
		this.stock.setTrailingDividendYield("0.02");
		assertTrue (this.stock.getTrailingDividendYield() == 0.02);
	}

}
