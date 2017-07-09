package com.marketduel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.marketduel.dao.MatchDao;
import com.marketduel.dao.PortfolioDao;
import com.marketduel.game.Match;
import com.marketduel.game.Portfolio;
import com.marketduel.game.StockHolding;
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

	@Autowired
	private PortfolioDao portfolioDao;

	@Autowired
	private MatchDao matchDao;

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

	public boolean addPlayerToQuickGame(int gameId, int playerId)
	{
		Portfolio p = new Portfolio();
		Game game = gamesDao.getGameById(gameId);
		ArrayList<Integer> gameMatchIds = game.getMatchIds();
		p.setGameId(gameId);
		p.setPlayerId(playerId);
		p.setMatchId(gameMatchIds.get(0)); //quickgame only has one match so we know it is the first match id

		//create a portfollio for the player to use in the quick game
		if (portfolioDao.createPortfolio(p)) {
			p = portfolioDao.getNewestPortfolioId();
			if (p == null) {
				return false;
			}
		}

		if (gamesDao.addPlayerToQuickGame(gameId, playerId, p.getPortfolioId())) {
			return true;
		}


		return false;
	}

	public boolean updateMatchsForGame(Game g, ArrayList<Integer> matchList)
	{
		return gamesDao.updateMatchIds(g, matchList);
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
	
	public void setGameDao(GameDao gamesDao) {
		this.gamesDao = gamesDao;
	}

	public Portfolio getPortfolioById (int pfId) {
		return portfolioDao.getPortfolioById(pfId);
	}

	public Boolean storeStockHoldingsInPortfolio (Portfolio pf, ArrayList<StockHolding> shList) {
		return portfolioDao.storeStockHoldingsInPortfolio(pf, shList);
	}

	public Match createMatch(Match m) {

		if (matchDao.createMatch(m)) {
			return matchDao.getNewestMatch();
		}

		return null;
	}

	public Game createGame(Game g) {
		if (gamesDao.createGame(g)) {
			return gamesDao.getNewestGame();
		}

		return null;
	}

	public List<Portfolio> getPlayerPortfolios(int playerId) {
		return portfolioDao.getPlayerPortfolios(playerId);
	}
}
