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

import com.marketduel.dao.MatchDao;
import com.marketduel.dao.PlayerDao;
import com.marketduel.game.ClosedMatch;
import com.marketduel.game.ContinuousMatch;
import com.marketduel.game.Match;
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
		String sql = "INSERT INTO matches (MatchName, StartDate, EndDate, InitialBudget, MatchType) values (:name, :start, :end, :budget, :type)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", m.getMatchName());
		params.put("start", m.getStartDate());
		params.put("end", m.getEndDate());
		params.put("budget", m.getInitialBalance());
		params.put("type", m.getMatchType());

		int result = template.update(sql, params);

		// A new add should modify 1 row
		return (result == 1);
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
	public Map<Integer, Integer> getPortfolioAndPlayerIDs(int matchID) {
		// TODO Auto-generated method stub
		return null;
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
        
        for(int i = 1; i < (Match.MAX_NUM_PLAYERS+1); i++)
        {
        	m.addPlayer(rs.getInt("Player"+i+"ID"));
        	m.addPortfolio(rs.getInt("Portfolio"+i+"ID"));
        }
        
		return m;
	};

}