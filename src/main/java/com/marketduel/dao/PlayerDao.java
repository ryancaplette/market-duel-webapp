package com.marketduel.dao;

import com.marketduel.model.Player;

public interface PlayerDao {
	
	Player getPlayerbyUsername(String username);
	
	Boolean registerPlayer(Player player);
	
	Boolean deletePlayerbyUsername(String username);
}