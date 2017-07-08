package com.marketduel.dao;

import java.util.ArrayList;
import java.util.List;

import com.marketduel.game.Game;
import com.marketduel.model.Player;

public interface GameDao {
	
	Boolean createGame(Game g);
	
	Game getGameById(int gameId);

	Game getNewestGame();

	Boolean deleteGameByID(int gameId);

	Boolean updateMatchIds(Game g, ArrayList<Integer> matchList);
	
	List<Game> getPlayersGames(Player p);

	List<Game> getAvailableGames();

	boolean addPlayerToQuickGame(int gameId, int PlayerId);
}