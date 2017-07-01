package com.marketduel.test;

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
import com.mysql.cj.jdbc.MysqlDataSource;

@ContextConfiguration(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
public class TestMatchDAO {
	
	private MatchDao matchDao;
	
	@Test
	@Transactional
	public void testGetPortfolioAndPlayerIDs() {
		matchDao = new MatchDaoImpl(dataSource());
		
		//This is temporary unit test - need to make own match just for test with values
		//and call on that rather than select second match as that is real data
		Map<Integer,Integer> playerPorfolioMap = matchDao.getPortfolioAndPlayerIDs(2);
		
		System.out.println(playerPorfolioMap.get(1));
		
		assert (playerPorfolioMap.keySet() != null);

	}

	
	
	public DataSource dataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://sweng500-db.cmvcqoa7soac.us-west-2.rds.amazonaws.com:3306/sweng_500_db");
		dataSource.setUser("dbmasteruser");
		dataSource.setPassword("sweng500*");
		return dataSource;
	}
}
