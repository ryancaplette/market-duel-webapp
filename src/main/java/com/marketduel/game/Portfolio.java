package com.marketduel.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

import com.marketduel.config.DatabaseConfig;
import com.marketduel.dao.impl.MatchDaoImpl;
import com.marketduel.dao.impl.PlayerDaoImpl;
import com.marketduel.dao.impl.PortfolioDaoImpl;
import com.marketduel.model.Player;

public class Portfolio {
	private int portfolioId;

	private float initialValue;
	private ArrayList<StockHolding> stockHoldings=new ArrayList<StockHolding>();
	private int gameId;
	private int matchId;
	private int playerId;
	private float balance;
	
	public static final int MAX_NUM_HOLDINGS = 10;
	
	public Portfolio() {

	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public void updateHoldingPrices()
	{
		ListIterator<StockHolding> itr = stockHoldings.listIterator();
		while(itr.hasNext()) {
			itr.next().updatePurchasePrice();
		}
	}
	
	/*
	public void setInitialValue() {
		float totalValue = 0.0f;
		ListIterator<StockHolding> itr = stockHoldings.listIterator();
		
		while(itr.hasNext()) {
			totalValue += itr.next().getPurchaseValue();
		}
	
		initialValue = totalValue;
	}
	*/
	public float getInitialValue() {
		float totalValue = 0.0f;
		ListIterator<StockHolding> itr = stockHoldings.listIterator();
		
		while(itr.hasNext()) {
			totalValue += itr.next().getPurchaseValue();
		}
	
		return totalValue;
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

	// This method is used by the DAO to populate the portfolio from the database
	public void addHolding(StockHolding sh) {
		stockHoldings.add(sh);
	}
	
	
	// This method is used to add new stocks to the portfolio
	public void addHoldingToPortfolio(StockHolding sh) {
		stockHoldings.add(sh);
		balance -= sh.getPurchaseValue();
	}
	
	// This method is used to remove stocks to the portfolio
	public void removeHoldingFromPortfolio(int holdingIndex) {
		balance += stockHoldings.get(holdingIndex).getCurrentValue();
		stockHoldings.remove(holdingIndex);
	}

	public int getPortfolioId() {
		return portfolioId;
	}
	
	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public float getCurrentValue() {
		float totalValue = 0.0f;
		ListIterator<StockHolding> itr = stockHoldings.listIterator();
		
		while(itr.hasNext()) {
			totalValue += itr.next().getCurrentValue();
		}
	
		return totalValue;
	}

	public int getNumStockHoldings()
	{
		return stockHoldings.size();
		
	}

	public ArrayList<StockHolding> getStockHoldings() {
		return stockHoldings;
	}

	public float getValueAtDate(Date endDate) {
		float totalValue = 0.0f;
		ListIterator<StockHolding> itr = stockHoldings.listIterator();
		
		while(itr.hasNext()) {
			totalValue += itr.next().getValueAtDate(endDate);
		}
	
		return totalValue;
	}
	
	public String getPlayerUsername() {
		DatabaseConfig d = new DatabaseConfig();
		PlayerDaoImpl p = new PlayerDaoImpl(d.dataSource());

    	Player player = p.getPlayerById(playerId);
    	
    	return player.getUsername();
	}

	public void setBalance(float initialBalance) {
		balance = initialBalance;
	}
	
	public float getBalance()
	{
		return balance;
	}
	
	public void updateBalance()
	{
		DatabaseConfig d = new DatabaseConfig();
		PortfolioDaoImpl p = new PortfolioDaoImpl(d.dataSource());

    	p.updateBalance(this);
	}
}
