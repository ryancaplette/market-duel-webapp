package com.marketduel.game;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import com.marketduel.config.DatabaseConfig;
import com.marketduel.dao.impl.MatchDaoImpl;
import com.marketduel.dao.impl.PlayerDaoImpl;
import com.marketduel.dao.impl.PortfolioDaoImpl;
import com.marketduel.game.Match.MatchType;
import com.marketduel.model.Player;

public class ContinuousMatch extends Match {

	public ContinuousMatch() {
		// TODO Auto-generated constructor stub
	}
	
	public ContinuousMatch(int plyrs, Date start, int duration, MatchType matchType) {
		setMaxPlayersInMatch(plyrs);
		setStartDate(start);
		setDuration(duration);
		setMatchType(matchType);
	}
	
	public ContinuousMatch(int plyrs, Date start, int duration, MatchType matchType, int balance) {
		setMaxPlayersInMatch(plyrs);
		setStartDate(start);
		setDuration(duration);
		setMatchType(matchType);
		setInitialBalance(balance);
	}

	@Override
	public void startMatch() {
	}

	@Override
	public void endMatch() {
		if(getWinnerId() == 0)
		{
			DatabaseConfig d = new DatabaseConfig();
			PlayerDaoImpl pd = new PlayerDaoImpl(d.dataSource());
			MatchDaoImpl md = new MatchDaoImpl(d.dataSource());
			
			//Get winner
			setWinnerId(determineWinner());
			
			//Store winner for match in DB
			md.updateWinner(this, getWinnerId());
			
			//Update player stats
			Player winningPlayer = pd.getPlayerById(getWinnerId());
			pd.updatePlayerWins(winningPlayer);
		}
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
		Iterator<Map.Entry<Integer, Integer>> it = portfolioPlayerMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pair = it.next();

			// Get value of portfolio at end date
			Portfolio pfl = p.getPortfolioById(pair.getValue());
			portfolioValue = pfl.getValueAtDate(getEndDate());

			//Update Player overall profits
			Player player = pldao.getPlayerById(pair.getKey());
			System.out.println("Values " + portfolioValue + " " + this.getInitialBalance());
			pldao.updateTotalPlayerProfits(player, (portfolioValue - this.getInitialBalance()));

			//Determine if portfolio is max for match
			if (portfolioValue > maxPortfolioValue)
			{
				// Set new winner if portfolio value is greater than the others that have been iterated over
				maxPortfolioValue = portfolioValue;
				winnerID = pair.getKey();
			}
		}

		// Return the ID of the winner
		return winnerID;
	}

	@Override
	public boolean isTradingActive() {
		// During a continuous match, trading is open during the draft period and between the match start and end times

		if(this.isDraftActive() || this.isMatchActive())
		{
			return true;
		}
		
		return false;
	}

}
