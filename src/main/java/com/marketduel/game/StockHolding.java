package com.marketduel.game;

public class StockHolding {

	private String ticker;
	private float shares;
	private float price;
	
	
	public StockHolding(String ticker, float shares, float price) {
		this.ticker = ticker;
		this.shares = shares;
		this.price = price;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public float getShares() {
		return shares;
	}


	public void setShares(float shares) {
		this.shares = shares;
	}


	public String getTicker() {
		return ticker;
	}


	public void addShares(float shareAdjustment) {
		this.shares += shareAdjustment;
	}


	public void updatePrice() {
		float currentPrice = 0.0f;
		// Get current value from API
		// currentValue = ?
		this.price = currentPrice;
	}
	
	public float getValue() {
		return shares*price;
	}
}
