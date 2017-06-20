package com.marketduel.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketduel.dao.GameDao;
import com.marketduel.dao.PlayerDao;
import com.marketduel.game.Game;
import com.marketduel.model.LoginResult;
import com.marketduel.model.Player;
import com.marketduel.util.PasswordUtil;

@Service
public class MarketDuelService {
	
	@Autowired
	private PlayerDao playerDao;
	
	@Autowired
	private GameDao gamesDao;

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
	
	public ArrayList<Game> getGamesForPlayer(Player p)
	{
		return (ArrayList<Game>) gamesDao.getPlayersGames(p);
	}

	public ArrayList<Game> getAvailableGames()
	{
		return (ArrayList<Game>) gamesDao.getAvailableGames();
	}

	public boolean addPlayerToGame(int gameId, int playerId)
	{
		return gamesDao.addPlayerToGame(gameId, playerId);
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
	
	public void setGameDao(GameDao gamesDao) {
		this.gamesDao = gamesDao;
	}

}
