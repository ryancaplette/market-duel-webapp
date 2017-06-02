package com.marketduel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketduel.dao.PlayerDao;
import com.marketduel.model.LoginResult;
import com.marketduel.model.Player;
import com.marketduel.util.PasswordUtil;

@Service
public class MarketDuelService {
	
	@Autowired
	private PlayerDao playerDao;

	public Player getPlayerbyUsername(String username) {
		return playerDao.getPlayerbyUsername(username);
	}
	
	public LoginResult checkPlayer(Player player) {
		LoginResult result = new LoginResult();
		Player playerFound = playerDao.getPlayerbyUsername(player.getUsername());
		if(playerFound == null) {
			result.setError("Invalid username!");
		} else if(!PasswordUtil.verifyPassword(player.getPassword(), playerFound.getPassword())) {
			result.setError("Invalid password!");
		} else {
			result.setPlayer(playerFound);
		}
		
		return result;
	}
	
	public void registerPlayer(Player player) {
		player.setPassword(PasswordUtil.hashPassword(player.getPassword()));
		playerDao.registerPlayer(player);
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}

}
