package com.marketduel.test;

import java.util.Calendar;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.marketduel.App;
import com.marketduel.dao.MatchDao;
import com.marketduel.dao.impl.MatchDaoImpl;
import com.marketduel.game.ClosedMatch;
import com.marketduel.game.Match;
import com.mysql.cj.jdbc.MysqlDataSource;

@ContextConfiguration(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
public class TestMatchDAO {
	
	private MatchDao matchDao;
	
	@Test
	@Transactional
	public void testCreateReadDeleteMatch() {
		matchDao = new MatchDaoImpl(dataSource());
		
		//Create Match
		Match m = new ClosedMatch();
		m.setMatchName("UTMatch");
		m.setStartDate(Calendar.getInstance().getTime());
		m.setEndDate(Calendar.getInstance().getTime());
		m.setInitialBalance(1000);
	    Boolean res = matchDao.createMatch(m);
		
		assert (res == true);
		
		//Read
	    m = matchDao.getMatchByName("UTMatch");
		
		//Delete
		matchDao.deleteMatchByID(m.getMatchID());
	}
	
	
	@Test
	@Transactional
	public void testGetPortfolioAndPlayerIDs() {
		matchDao = new MatchDaoImpl(dataSource());
		
		//Create Match
		Match m = new ClosedMatch();
		m.setMatchName("UTMatch");
		m.setStartDate(Calendar.getInstance().getTime());
		m.setEndDate(Calendar.getInstance().getTime());
		m.setInitialBalance(1000);
		matchDao.createMatch(m);
		
		//Read
	    m = matchDao.getMatchByName("UTMatch");
		
		//Update
		m.addPlayer(1);
		m.addPortfolio(1);
		
		matchDao.updatePlayerIds(m, m.getPlayerIds());
		matchDao.updatePortfolioIds(m, m.getPortfolioIds());
		
		//Test
		Map<Integer,Integer> playerPorfolioMap = matchDao.getPortfolioAndPlayerIDs(m.getMatchID());
		
		System.out.println(playerPorfolioMap.get(1));
		
		assert (playerPorfolioMap.keySet() != null);
		
		//Delete
		matchDao.deleteMatchByID(m.getMatchID());
	}

	
	
	public DataSource dataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://sweng500-db.cmvcqoa7soac.us-west-2.rds.amazonaws.com:3306/sweng_500_db");
		dataSource.setUser("dbmasteruser");
		dataSource.setPassword("sweng500*");
		return dataSource;
	}
}
