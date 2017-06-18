package com.marketduel.dao;

import java.util.ArrayList;

import com.marketduel.game.Match;
import com.marketduel.game.Portfolio;
import com.marketduel.model.Player;

public interface MatchDao {
	
	Boolean createMatch(Match m);
	
	Match getMatchById(int matchId);
	
	Boolean deleteMatchByID(int matchId);

	Boolean updatePlayerIds(Match m, ArrayList<Integer> plList);

	Boolean updatePortfolioIds(Match m, ArrayList<Integer> portList);
}