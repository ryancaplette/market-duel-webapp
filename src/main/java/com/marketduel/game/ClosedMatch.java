package com.marketduel.game;

import java.util.Date;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import com.marketduel.dao.impl.MatchDaoImpl;
import com.marketduel.dao.impl.PlayerDaoImpl;
import com.marketduel.dao.impl.PortfolioDaoImpl;
import com.marketduel.game.Portfolio;
import com.marketduel.model.Player;
import com.marketduel.config.DatabaseConfig;

public class ClosedMatch extends Match {

	public ClosedMatch(){};
	
	public ClosedMatch(int plyrs, Date start, int duration, MatchType matchType) {
		setMaxPlayersInMatch(plyrs);
		setStartDate(start);
		setDuration(duration);
		setMatchType(matchType);
	}
	
	public ClosedMatch(int plyrs, Date start, int duration, MatchType matchType, int balance) {
		setMaxPlayersInMatch(plyrs);
		setStartDate(start);
		setDuration(duration);
		setMatchType(matchType);
		setInitialBalance(balance);
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
		DatabaseConfig d = new DatabaseConfig();
		PlayerDaoImpl pd = new PlayerDaoImpl(d.dataSource());
		
		//Get and update winning player stats
		int winningPlayerId = determineWinner();
		Player winningPlayer = pd.getPlayerById(winningPlayerId);
		pd.updatePlayerWins(winningPlayer);
	}

	// Determine winner of a match. Winner's player ID is returned. Currently does not support ties.
	protected int determineWinner() {
		DatabaseConfig d = new DatabaseConfig();
		MatchDaoImpl m = new MatchDaoImpl(d.dataSource());
		PortfolioDaoImpl p = new PortfolioDaoImpl(d.dataSource());
		PlayerDaoImpl pldao = new PlayerDaoImpl(d.dataSource());
		
		float portfolioValue = 0.0f;
		float maxPortfolioValue = Float.NEGATIVE_INFINITY;
		int winnerID = -1;
		
		// Get player ID/portfolio ID pairs from the database
		Map<Integer, Integer> portfolioPlayerMap = m.getPortfolioAndPlayerIDs(getMatchID());
		
		// Iterate over portfolio ID list
		for (Integer key : portfolioPlayerMap.keySet()) {
			// Get value of portfolio at end date
			Portfolio pfl = p.getPortfolioById(key);
		    portfolioValue = pfl.getValueAtDate(getEndDate());
			
			//Update Player overall profits
			Player player = pldao.getPlayerById(portfolioPlayerMap.get(key));
			pldao.updateTotalPlayerProfits(player, (portfolioValue - pfl.getInitialValue()));
			
			//Determine if portfolio is max for match
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

	@Override
	public boolean isTradingActive() {
		// In a closed match, trading is only active during the draft period
		
		return this.isDraftActive();
	}
	
}
