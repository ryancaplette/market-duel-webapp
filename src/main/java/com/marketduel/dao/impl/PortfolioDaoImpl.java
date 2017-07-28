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

import com.marketduel.dao.PortfolioDao;
import com.marketduel.game.Portfolio;
import com.marketduel.game.StockHolding;
import com.marketduel.model.Player;


@Repository
public class PortfolioDaoImpl implements PortfolioDao {
	
	private NamedParameterJdbcTemplate template;

	@Autowired
	public PortfolioDaoImpl(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	/**
	 * Creates an empty portfolio
	 */
	public Boolean createPortfolio(Portfolio pf) {
		String sql = "INSERT INTO portfolio (PortfolioID, MatchID, PlayerID) VALUES (:portfolioId, :matchId, :playerId)";

		Map<String, Object> params = new HashMap<String, Object>();
        params.put("portfolioId", pf.getPortfolioId());
        params.put("matchId", pf.getMatchId());
        params.put("playerId", pf.getPlayerId());
        params.put("balance", pf.getBalance());

        int result = template.update(sql, params);
	
        //A new add should modify 1 row
        return (result == 1);
	}

	@Override
	/**
	 * Gets a portfolio from the database
	 */
	public Portfolio getNewestPortfolioId() {
		String sql = "SELECT * FROM portfolio ORDER BY PortfolioId DESC LIMIT 1";


		List<Portfolio> list = template.query(
				sql,
				portfolioMapper);

		Portfolio result = null;
		if(list != null && !list.isEmpty()) {
			result = list.get(0);
		}

		return result;
	}

	@Override
	/**
	 * Gets a portfolio from the database
	 */
	public Portfolio getPortfolioById(int pfId) {
		String sql = "SELECT * FROM portfolio WHERE PortfolioID=:pfid";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("pfid", pfId);
		
        List<Portfolio> list = template.query(
                    sql,
                    params,
                    portfolioMapper);
        
        Portfolio result = null;
        if(list != null && !list.isEmpty()) {
        	result = list.get(0);
        }
        
		return result;
	}

	@Override
	/**
	 * Gets all portfolios related to a player id
	 */
	public List<Portfolio> getPlayerPortfolios(int playerId) {
		String sql = "SELECT * FROM portfolio WHERE PlayerID=:playerId";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerId", playerId);

		List<Portfolio> list = template.query(
				sql,
				params,
				portfolioMapper);

		if(list != null && !list.isEmpty()) {
			return list;
		}

		return null;
	}

	@Override
	/**
	 * Gets all portfolios related to a match id
	 */
	public List<Portfolio> getPortfoliosForMatchId(int matchId) {
		String sql = "SELECT * FROM portfolio WHERE MatchID=:matchId";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("matchId", matchId);

		List<Portfolio> list = template.query(
				sql,
				params,
				portfolioMapper);

		if(list != null && !list.isEmpty()) {
			return list;
		}

		return null;
	}

	@Override
	/**
	 * Will store the given stock holding list into the portfolio
	 */
	public Boolean storeStockHoldingsInPortfolio(Portfolio pf, ArrayList<StockHolding> shList) {
		int result = -1;
		//Build string pointing to next empty StockHolding
		for (int i = 0; i < shList.size() && result != 0 ; i++) {
			String stockStr = "Stock" + (i + 1);
			String sql = "UPDATE portfolio " +
			             "SET " + stockStr + "Symb = :symb, " + stockStr + "NumShares = :numShares, " + stockStr + "InitCost = :initCost " +
					     "WHERE  PortfolioID = " + pf.getPortfolioId();

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("symb", shList.get(i).getTicker().toUpperCase());
			params.put("numShares", shList.get(i).getShares());
			params.put("initCost", shList.get(i).getPurchasePrice());

			result = template.update(sql, params);
		}
        //Modifies one portfolio
        return (result != 0);
	}

	@Override
	public Boolean deletePortfolioByID(int pfId) {
		String sql = "DELETE FROM portfolio WHERE PortfolioId=:id";
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", pfId);
		
		int result = template.update(sql, params);
        //A delete should modify 1 row
        return (result == 1);
	}
	
	@Override
	/**
	 * Updates the portfolio balance
	 */
	public Boolean updateBalance(Portfolio pf) {
		int result = -1;
		
		String sql = "UPDATE portfolio " +
	             "SET Balance = :bal " +
			     "WHERE  PortfolioID = " + pf.getPortfolioId();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bal", pf.getBalance());

		result = template.update(sql, params);

        //Modifies one portfolio
        return (result != 0);
	}
	
	private RowMapper<Portfolio> portfolioMapper = (rs, rowNum) -> {
		Portfolio pf = new Portfolio();
		
		pf.setPortfolioId(rs.getInt("PortfolioID"));
		pf.setMatchId(rs.getInt("MatchID"));
		pf.setPlayerId(rs.getInt("PlayerID"));
		pf.setBalance(rs.getFloat("Balance"));
		for(int i = 1; i <= Portfolio.MAX_NUM_HOLDINGS; i++){
			String stockStr = "Stock" + (i);
			if (rs.getString(stockStr + "Symb") == null) {
				continue;
			}
			StockHolding sh = new StockHolding(rs.getString(stockStr + "Symb"), rs.getInt(stockStr + "NumShares"), rs.getFloat(stockStr + "InitCost"));
			pf.addHolding(sh);
		}
		return pf;
	};
}