package com.marketduel.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.marketduel.game.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.marketduel.dao.GameDao;
import com.marketduel.game.Game;
import com.marketduel.game.LeagueGame;
import com.marketduel.game.QuickGame;
import com.marketduel.model.Player;

@Repository
public class GameDaoImpl implements GameDao {
	
	private NamedParameterJdbcTemplate template;

	@Autowired
	public GameDaoImpl(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	public Boolean createGame(Game g) {
		String sql;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", g.getType() == Game.GameType.QUICK ? 0:1);
		params.put("start", g.getFirstMatchStart());
		params.put("dur", g.getMatchDurationInDays());
		params.put("cont", g.getContinuous());
		params.put("name", g.getGameName());

		if(g.getType() == Game.GameType.QUICK)
		{
			sql = "INSERT INTO game (GameType, FirstMatchStart, MatchDurationDays, IsContinuous, Match1Id, GameName) values (:type, :start, :dur, :cont, :matchId, :name)";
			params.put("matchId", g.getMatchIds().get(0));
		}
		else
		{
			sql = "INSERT INTO game (GameType, FirstMatchStart, MatchDurationDays, IsContinuous) values (:type, :start, :dur, :cont)";
		}
		
		int result = template.update(sql, params);

		// A new add should modify 1 row
		return (result == 1);
	}

	@Override
	public Game getNewestGame() {
		String sql = "SELECT * FROM game ORDER BY GameID DESC LIMIT 1";


		List<Game> list = template.query(
				sql,
				gameMapper);

		Game result = null;
		if(list != null && !list.isEmpty()) {
			result = list.get(0);
		}

		return result;
	}

	@Override
	public Game getGameById(int gameId) {
		String sql = "SELECT * FROM game WHERE GameId=:id";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", gameId);
		
        List<Game> list = template.query(
                    sql,
                    params,
                    gameMapper);
        
        Game result = null;
        if(list != null && !list.isEmpty()) {
        	result = list.get(0);
        }
        
		return result;
	}

	@Override
	public Boolean updateMatchIds(Game g, ArrayList<Integer> matchList) {
		int result = -1;
		//Build string pointing to next empty StockHolding
		for (int i = 0; i < matchList.size() && result != 0 ; i++) {
			String matchStr = "Match" + (i + 1) + "ID";
			String sql = "UPDATE game " +
			             "SET " + matchStr + " = :id " +
					     "WHERE GameID = " + g.getGameId();

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", matchList.get(i));


			result = template.update(sql, params);
		}
        //Return no failures
        return (result != 0);	
	}

	@Override
	public Boolean deleteGameByID(int gameId) {
		String sql = "DELETE FROM game WHERE GameId=:id";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", gameId);
		
		int result = template.update(sql, params);
        //A delete should modify 1 row
        return (result == 1);
	}
	
	private RowMapper<Game> gameMapper = (rs, rowNum) -> {
		Game g;
		if (rs.getInt("GameType") == 0)
		{
			g = new QuickGame();
			g.setType(Game.GameType.QUICK);
		}
		else
		{
			g = new LeagueGame();
			g.setType(Game.GameType.LEAGUE);
		}
		
		g.setGameId(rs.getInt("GameID"));
		g.setFirstMatchStart(rs.getDate("FirstMatchStart"));
		g.setMatchDurationInDays(rs.getInt("MatchDurationDays"));
		g.setContinuous(rs.getBoolean("IsContinuous"));
		g.setGameName(rs.getString("GameName"));

        for(int i = 1; i < (Game.MAX_MATCHES_PER_GAME+1); i++)
        {
        	int matchId = rs.getInt("Match"+i+"Id");
        	if (matchId != 0) {
				g.addMatch(rs.getInt("Match"+i+"Id"));
			}
        }

		Match m = g.getFirstMatch();
        System.out.println("First Match is: " + m.getMatchID());
        System.out.println("Players in match is: " + m.getPlayersInMatch());

		int playersInGame = 0;
		if (m != null) {
			g.setCurPlayersInGame(m.getPlayersInMatch());
		}

        System.out.println("MatchListSize: " + g.getMatchIds().size());

		return g;
	};

	@Override
	public List<Game> getPlayersGames(Player p) {
		
		String sql ="SELECT DISTINCT * FROM game g "
			+ "JOIN ("
			+ "	 (SELECT MatchId FROM matches "
			+ "	  WHERE :playerId "
			+ "   IN (Player1ID, Player2ID, Player3ID, Player4ID, Player5ID, Player6ID, Player7ID, Player8ID, Player9ID, Player10ID))"
			+ ") m ON m.MatchID IN (Match1Id,Match2Id,Match3Id,Match4Id,Match5Id,Match6Id,Match7Id,Match8Id,Match9Id,Match10Id)";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("playerId", p.getPlayerId());
        
        System.out.println("PlayerId: " + p.getPlayerId());
		
        List<Game> playerGameList = template.query(
                sql,
                params,
                gameMapper);
		
		return playerGameList;
	}

	@Override
	public List<Game> getAvailableGames() {

		String sql ="SELECT DISTINCT * FROM game "
				+ "WHERE FirstMatchStart >= CURDATE()";

		List<Game> availableGameList = template.query(
				sql,
				gameMapper);

		return availableGameList;
	}

	@Override
	public boolean addPlayerToQuickGame(int gameId, int playerId, int portfolioId) {
		String sql ="SELECT DISTINCT * FROM game WHERE GameID = :gameId";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("gameId", gameId);

		List<Game> curGame = template.query(
				sql,
				params,
				gameMapper);
		
		int retVal = 0;
		if (curGame.size() == 1)
		{
			sql = "UPDATE matches"
			    + " SET Player10ID = CASE WHEN Player10ID IS NULL AND Player9ID IS NOT NULL THEN :playerId ELSE Player10ID END,"
			    + "     Player9ID = CASE WHEN Player9ID IS NULL AND Player8ID IS NOT NULL THEN :playerId ELSE Player9ID END,"
			    + "     Player8ID = CASE WHEN Player8ID IS NULL AND Player7ID IS NOT NULL THEN :playerId ELSE Player8ID END,"
			    + "     Player7ID = CASE WHEN Player7ID IS NULL AND Player6ID IS NOT NULL THEN :playerId ELSE Player7ID END,"
			    + "     Player6ID = CASE WHEN Player6ID IS NULL AND Player5ID IS NOT NULL THEN :playerId ELSE Player6ID END,"
			    + "     Player5ID = CASE WHEN Player5ID IS NULL AND Player4ID IS NOT NULL THEN :playerId ELSE Player5ID END,"
			    + "     Player4ID = CASE WHEN Player4ID IS NULL AND Player3ID IS NOT NULL THEN :playerId ELSE Player4ID END,"
			    + "     Player3ID = CASE WHEN Player3ID IS NULL AND Player2ID IS NOT NULL THEN :playerId ELSE Player3ID END,"
			    + "     Player2ID = CASE WHEN Player2ID IS NULL AND Player1ID IS NOT NULL THEN :playerId ELSE Player2ID END,"
			    + "     Player1ID = CASE WHEN Player1ID IS NULL THEN :playerId ELSE Player1ID END,"
				+ "     Portfolio10ID = CASE WHEN Portfolio10ID IS NULL AND Portfolio9ID IS NOT NULL THEN :portfolioId ELSE Portfolio10ID END,"
				+ "     Portfolio9ID = CASE WHEN Portfolio9ID IS NULL AND Portfolio8ID IS NOT NULL THEN :portfolioId ELSE Portfolio9ID END,"
				+ "     Portfolio8ID = CASE WHEN Portfolio8ID IS NULL AND Portfolio7ID IS NOT NULL THEN :portfolioId ELSE Portfolio8ID END,"
				+ "     Portfolio7ID = CASE WHEN Portfolio7ID IS NULL AND Portfolio6ID IS NOT NULL THEN :portfolioId ELSE Portfolio7ID END,"
				+ "     Portfolio6ID = CASE WHEN Portfolio6ID IS NULL AND Portfolio5ID IS NOT NULL THEN :portfolioId ELSE Portfolio6ID END,"
				+ "     Portfolio5ID = CASE WHEN Portfolio5ID IS NULL AND Portfolio4ID IS NOT NULL THEN :portfolioId ELSE Portfolio5ID END,"
				+ "     Portfolio4ID = CASE WHEN Portfolio4ID IS NULL AND Portfolio3ID IS NOT NULL THEN :portfolioId ELSE Portfolio4ID END,"
				+ "     Portfolio3ID = CASE WHEN Portfolio3ID IS NULL AND Portfolio2ID IS NOT NULL THEN :portfolioId ELSE Portfolio3ID END,"
				+ "     Portfolio2ID = CASE WHEN Portfolio2ID IS NULL AND Portfolio1ID IS NOT NULL THEN :portfolioId ELSE Portfolio2ID END,"
				+ "     Portfolio1ID = CASE WHEN Portfolio1ID IS NULL THEN :portfolioId ELSE Portfolio1ID END "
			    + "WHERE MatchID = :matchId";
			params = new HashMap<String, Object>();
	        params.put("matchId", curGame.get(0).getMatchIds().get(0));
	        params.put("playerId", playerId);
	        params.put("portfolioId", portfolioId);

	        retVal = template.update(sql, params);
	        
	        System.out.println("Added player to game: " + retVal);

		}
		
		//Need to create portfolio in next open spot of match for new player added to match

		return (retVal == 1);
	}

}