package com.marketduel.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

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
		String sql = "INSERT INTO game (GameType, FirstMatchStart, MatchDurationDays, IsContinuous) values (:type, :start, :dur, :cont)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", g.getType());
		params.put("start", g.getFirstMatchStart());
		params.put("dur", g.getMatchDurationInDays());
		params.put("cont", g.getContinuous());

		int result = template.update(sql, params);

		// A new add should modify 1 row
		return (result == 1);
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
			String portStr = "Portfolio" + (i + 1) + "ID";
			String sql = "UPDATE matches " +
			             "SET " + portStr + " = :id " +
					     "WHERE MatchID = " + g.getGameId();

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
		}
		else
		{
			g = new LeagueGame();
		}
		
		g.setGameId(rs.getInt("GameID"));
		//g.setType(rs.getInt("GameType"));
		g.setFirstMatchStart(rs.getDate("FirstMatchStart"));
		g.setMatchDurationInDays(rs.getInt("MatchDurationDays"));
		g.setContinuous(rs.getBoolean("IsContinuous"));
		
        for(int i = 1; i < (Game.MAX_MATCHES_PER_GAME+1); i++)
        {
        	g.addMatch(rs.getInt("Match"+i+"ID"));
        }
        
		return g;
	};

	@Override
	public List<Game> getPlayersGames(Player p) {
		
		String sql ="SELECT DISTINCT * FROM game "
			+ "WHERE "
			+ "	 (SELECT MatchId FROM matches "
			+ "	  WHERE :playerId "
			+ "   IN (Player1ID, Player2ID, Player3ID, Player4ID, Player5ID, Player6ID, Player7ID, Player8ID, Player9ID, Player10ID))"
			+ "IN (Match1Id,Match2Id,Match3Id,Match4Id,Match5Id,Match6Id,Match7Id,Match8Id,Match9Id,Match10Id)";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("playerId", p.getPlayerId());
        
        System.out.println("PlayerId: " + p.getPlayerId());
		
        List<Game> playerGameList = template.query(
                sql,
                params,
                gameMapper);
		
		return playerGameList;
	}

}