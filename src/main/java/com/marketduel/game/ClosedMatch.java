package com.marketduel.game;

import java.util.Date;
import java.util.ListIterator;
import java.util.Map;
import com.marketduel.dao.impl.MatchDaoImpl;
import com.marketduel.dao.impl.PortfolioDaoImpl;
import com.marketduel.config.DatabaseConfig;

import com.mysql.cj.api.mysqla.result.Resultset.Type;

public class ClosedMatch extends Match {

	public ClosedMatch(){};
	
	public ClosedMatch(int plyrs, Date start, int duration, MatchType matchType) {
		setMaxPlayersInMatch(plyrs);
		setStartDate(start);
		setDuration(duration);
		setMatchType(matchType);
	}

	@Override
	void startMatch() {
		// Set portfolio value equal to initial balance for each portfolio
//		ListIterator<Portfolio> itr = portfolios.listIterator();
//		while(itr.hasNext()) {
//			itr.next().equalizePortfolioValues(getInitialBalance());
//		}
	}

	@Override
	void endMatch() {
		determineWinner();
		// Log values and determine winner
//		float[] gains = new float[portfolios.size()];
//		
//		for (int i=0; i < portfolios.size(); i++)
//		{
//			gains[0] = portfolios.get(i).getCurrentValue() - portfolios.get(i).getInitialValue();
//		}
//		
//		determineWinner(gains);
	}

	// Determine winner of a match. Winner's player ID is returned. Currently does not support ties.
	protected int determineWinner() {
		DatabaseConfig d = new DatabaseConfig();
		MatchDaoImpl m = new MatchDaoImpl(d.dataSource());
		PortfolioDaoImpl p = new PortfolioDaoImpl(d.dataSource());
		
		float portfolioValue = 0.0f;
		float maxPortfolioValue = Float.NEGATIVE_INFINITY;
		int winnerID = -1;
		
		// Get player ID/portfolio ID pairs from the database
		Map<Integer, Integer> portfolioPlayerMap = m.getPortfolioAndPlayerIDs(getMatchID());
		
		// Iterate over portfolio ID list
		for (Integer key : portfolioPlayerMap.keySet()) {
			// Get current value of portfolio
		    //portfolioValue = p.getPortfolioById(key).getCurrentValue();
			// Get value of portfolio at end date
			portfolioValue = p.getPortfolioById(key).getValueAtDate(getEndDate());
		    if (portfolioValue > maxPortfolioValue)
		    {
		    	// Set new winner if portfolio value is greater than the others that have been iterated over
		    	maxPortfolioValue = portfolioValue;
		    	winnerID = portfolioPlayerMap.get(key);
		    }
		}
		
		// Return the ID of the winner
		return winnerID;
	}
	
//	private void determineWinner(float[] gains) {
//		float max = gains[0];
//		int index = 0;
//		int winningPortfolioId = 0;
//		
//		for (int i=1; i < portfolios.size(); i++)
//		{
//			if(gains[i] > max)
//			{
//				max = gains[i];
//				index = i;
//			}
//		}
//		
//		winningPortfolioId = portfolios.get(index).getPortfolioId();
//		
//		// DATABASE LOOKUP: find winner name from portfolioId
//		// winner = winnerName;
//	}

	
}
