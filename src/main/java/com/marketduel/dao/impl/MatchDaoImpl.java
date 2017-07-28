package com.marketduel.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.marketduel.dao.MatchDao;
import com.marketduel.dao.PlayerDao;
import com.marketduel.game.ClosedMatch;
import com.marketduel.game.ContinuousMatch;
import com.marketduel.game.Match;
import com.marketduel.game.Match.MatchType;
import com.marketduel.game.Portfolio;
import com.marketduel.model.Player;

@Repository
public class MatchDaoImpl implements MatchDao {
	
	private NamedParameterJdbcTemplate template;

	@Autowired
	public MatchDaoImpl(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	public Boolean createMatch(Match m) {
		String sql = "INSERT INTO matches (MatchName, StartDate, EndDate, DraftTime, InitialBudget, MatchType, NumPlayers) values (:name, :start, :end, :draft, :budget, :type, :players)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", m.getMatchName());
		params.put("start", m.getStartDate());
		params.put("end", m.getEndDate());
		params.put("draft", m.getDraftStartDate());
		params.put("budget", m.getInitialBalance());
		params.put("type", m.getMatchType() == MatchType.Closed ? 0:1);
		params.put("players", m.getPlayersInMatch());

		int result = template.update(sql, params);

		// A new add should modify 1 row
		return (result == 1);
	}

	@Override
	public Match getNewestMatch() {
		String sql = "SELECT * FROM matches ORDER BY MatchID DESC LIMIT 1";

		List<Match> list = template.query(
				sql,
				matchMapper);

		Match result = null;
		if(list != null && !list.isEmpty()) {
			result = list.get(0);
		}

		return result;
	}

	@Override
	public Match getMatchById(int matchId) {
		String sql = "SELECT * FROM matches WHERE MatchId=:id";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", matchId);
		
        List<Match> list = template.query(
                    sql,
                    params,
                    matchMapper);
        
        Match result = null;
        if(list != null && !list.isEmpty()) {
        	result = list.get(0);
        }
        
		return result;
	}
	
	@Override
	public Match getMatchByName(String matchName) {
		String sql = "SELECT * FROM matches WHERE MatchName=:name";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", matchName);
		
        List<Match> list = template.query(
                    sql,
                    params,
                    matchMapper);
        
        Match result = null;
        if(list != null && !list.isEmpty()) {
        	result = list.get(0);
        }
        
		return result;
	}

	@Override
	public Boolean updatePlayerIds(Match m, ArrayList<Integer> plList) {
		int result = -1;
		//Build string pointing to next empty StockHolding
		for (int i = 0; i < plList.size() && result != 0 ; i++) {
			String plStr = "Player" + (i + 1) + "ID";
			String sql = "UPDATE matches " +
			             "SET " + plStr + " = :id " +
					     "WHERE MatchID = " + m.getMatchID();

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", plList.get(i));


			result = template.update(sql, params);
		}
        //Return no failures
        return (result != 0);		
	}

	@Override
	public Boolean updatePortfolioIds(Match m, ArrayList<Integer> portList) {
		int result = -1;
		//Build string pointing to next empty StockHolding
		for (int i = 0; i < portList.size() && result != 0 ; i++) {
			String portStr = "Portfolio" + (i + 1) + "ID";
			String sql = "UPDATE matches " +
			             "SET " + portStr + " = :id " +
					     "WHERE MatchID = " + m.getMatchID();

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", portList.get(i));


			result = template.update(sql, params);
		}
        //Return no failures
        return (result != 0);	
	}

	@Override
	public Boolean deleteMatchByID(int matchId) {
		String sql = "DELETE FROM matches WHERE MatchId=:id";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", matchId);
		
		int result = template.update(sql, params);
        //A delete should modify 1 row
        return (result == 1);
	}
	
	
	// Return a map of all the portfolios and players in the game
	@Override
	public Map<Integer, Integer> getPortfolioAndPlayerIDs(int matchId) {
		String sql = "SELECT * FROM matches WHERE MatchId=:id";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", matchId);
        
        //List should be size 1 since MatchId are guaranteed unique in SQL table
		List<Map<Integer, Integer>> list = template.query(sql, params, playerPorfolioMapper);
		
		return list.get(0);
	}
	
	private RowMapper<Match> matchMapper = (rs, rowNum) -> {
		Match m;
		if (rs.getInt("MatchType") == 0)
		{
			m = new ClosedMatch();
		}
		else
		{
			m = new ContinuousMatch();
		}
		
		m.setMatchID(rs.getInt("MatchID"));
        m.setMatchName(rs.getString("MatchName"));
        m.setStartDate(rs.getDate("StartDate"));
        m.setEndDate(rs.getDate("EndDate"));
        m.setInitialBalance(rs.getFloat("InitialBudget"));
        // Had trouble getting both the date and time to read in from the database. Code below should be updated to get both date and time. Currently it gets the date then sets a time of 1pm so I could test the functionality
        try {
        	SimpleDateFormat draftDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        	if (rs.getString("DraftTime") != null) {
				m.setDraftStartDate(draftDateFormat.parse(rs.getString("DraftTime")));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        for(int i = 1; i < (Match.MAX_NUM_PLAYERS+1); i++)
        {
        	m.addPlayer(rs.getInt("Player"+i+"ID"));
        	m.addPortfolio(rs.getInt("Portfolio"+i+"ID"));
        }
        
		return m;
	};
	
	private RowMapper<Map<Integer, Integer>> playerPorfolioMapper = (rs, rowNum) -> {
		Map<Integer, Integer> playerPortfolioLink = new HashMap<Integer, Integer>();
		for (int i = 1; i < (Match.MAX_NUM_PLAYERS + 1); i++)
		{
			//Only put valid pairs in the map
			if (rs.getInt("Player" + i + "ID") != 0 && rs.getInt("Portfolio" + i + "ID") != 0)
			{
				playerPortfolioLink.put(rs.getInt("Player" + i + "ID"), rs.getInt("Portfolio" + i + "ID"));
			}
		}

		return playerPortfolioLink;
	};

}