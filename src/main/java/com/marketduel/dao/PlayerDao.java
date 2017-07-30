package com.marketduel.dao;

import com.marketduel.model.Player;
import java.util.List;

public interface PlayerDao {

	Player getPlayerbyUsername(String username);

	Player getPlayerById(int id);
	
	Boolean registerPlayer(Player player);
	
	Boolean deletePlayerbyUsername(String username);
	
	Boolean updatePlayerWins(Player player);
	
	List<Player> getMostWinsLeaderboard();
	
	List<Player> getHighestProfitLeaderboard();
	
	Boolean updateTotalPlayerProfits(Player player, float gameProfit);
}