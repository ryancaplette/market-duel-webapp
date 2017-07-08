package com.marketduel.dao;

import java.util.ArrayList;
import java.util.Map;

import com.marketduel.game.Match;
import com.marketduel.game.Portfolio;
import com.marketduel.model.Player;

public interface MatchDao {
	
	Boolean createMatch(Match m);
	
	Match getMatchById(int matchId);

	Match getNewestMatch();

	Boolean deleteMatchByID(int matchId);

	Boolean updatePlayerIds(Match m, ArrayList<Integer> plList);

	Boolean updatePortfolioIds(Match m, ArrayList<Integer> portList);
	
	// Return a map of all the portfolios and players in the game
	Map<Integer, Integer> getPortfolioAndPlayerIDs(int matchId);

	Match getMatchByName(String matchName);
}