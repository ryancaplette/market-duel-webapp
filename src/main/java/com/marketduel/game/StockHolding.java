package com.marketduel.game;

public class StockHolding {

	private String ticker;
	private float shares;
	private float purchasePrice;
	
	
	public StockHolding(String ticker, float shares, float price) {
		this.ticker = ticker;
		this.shares = shares;
		this.purchasePrice = price;
	}


	public float getPurchasePrice() {
		return purchasePrice;
	}


	public void setPurchasePrice(float price) {
		this.purchasePrice = price;
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

	public void updatePurchasePrice() {
		float currentPrice = 0.0f;
		// Get current value from API
		// currentValue = ?
		this.purchasePrice = currentPrice;
	}
	
	public float getPurchaseValue() {
		return shares*purchasePrice;
	}


	public float getCurrentValue() {
		float currentPrice = 0.0f;
		
		// Get currentPrice from API
		
		return shares*currentPrice;
	}
}