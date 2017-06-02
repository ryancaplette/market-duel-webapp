package com.marketduel.dao;

import com.marketduel.model.Player;

public interface PlayerDao {
	
	Player getPlayerbyUsername(String username);
	
	void registerPlayer(Player player);
}
