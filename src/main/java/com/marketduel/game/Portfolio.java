package com.marketduel.game;

import java.util.ArrayList;
import java.util.ListIterator;

public class Portfolio {
	private int portfolioId;
	private float initialValue;
	private ArrayList<StockHolding> stockHoldings=new ArrayList<StockHolding>();
	
	public Portfolio() {
		setPortfolioId();
	}
	
	public void updateHoldingPrices()
	{
		ListIterator<StockHolding> itr = stockHoldings.listIterator();
		while(itr.hasNext()) {
			itr.next().updatePurchasePrice();
		}
	}
	
	public void setInitialValue() {
		float totalValue = 0.0f;
		ListIterator<StockHolding> itr = stockHoldings.listIterator();
		
		while(itr.hasNext()) {
			totalValue += itr.next().getPurchaseValue();
		}
	
		initialValue = totalValue;
	}
	
	public float getInitialValue() {
		return initialValue;
	}
	
	/* Since players may add and remove stocks between the game start and the match start, the value of each portfolio
	 * shall be adjusted to match the balance set for the match. This is done by scaling the number of shares of each
	 * stock in the portfolio at the time of the start of the match so that each portfolio value equals the set balance. 
	 */
	public void equalizePortfolioValues(int balance) {
		float portfolioValue = 0.0f;
		float valueBalanceDifference = 0.0f;
		float differencePerStock = 0.0f;
		float shareAdjustment = 0.0f;
		int index = 0;
		
		ListIterator<StockHolding> itr = stockHoldings.listIterator();
		
		// Update each purchase price to match the current price at time of the start of the match
		while(itr.hasNext()) {
			itr.next().updatePurchasePrice();
		}
		
		// Get portfolio value
		portfolioValue = this.getInitialValue();
		
		// Calculate difference between portfolio value and initial balance
		valueBalanceDifference = portfolioValue - balance;
		
		// Divide difference by number of different stocks
		differencePerStock = valueBalanceDifference/(float)stockHoldings.size();
		// Round to two decimals
		differencePerStock = (float) (Math.round(differencePerStock*100.0)/100.0);
		
		// Change each stock's number of shares by the value of the stock divided by the differencePerStock
		while(itr.hasPrevious()) {
			index = itr.previousIndex();
			shareAdjustment = stockHoldings.get(index).getPurchaseValue()/differencePerStock;
			itr.previous().addShares(shareAdjustment);
		}
	}

	public void addHolding(StockHolding sh) {
		stockHoldings.add(sh);
	}
	
	public void removeHolding(int holdingIndex) {
		stockHoldings.remove(holdingIndex);
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId() {
		// Get next portfolio ID from database
		//this.portfolioId = getNextPortfolioId();
	}

	public float getCurrentValue() {
		float totalValue = 0.0f;
		ListIterator<StockHolding> itr = stockHoldings.listIterator();
		
		while(itr.hasNext()) {
			totalValue += itr.next().getCurrentValue();
		}
	
		return totalValue;
	}

}