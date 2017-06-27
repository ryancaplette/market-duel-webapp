package com.marketduel.test;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.sql.DataSource;

import com.marketduel.game.Portfolio;
import com.marketduel.game.StockHolding;
import com.marketduel.dao.PortfolioDao;
import com.marketduel.dao.impl.PortfolioDaoImpl;
import com.mysql.cj.jdbc.MysqlDataSource;

// Added this as a temporary test. Expected values are based on 6/26/17 and will need to be updated if run in the future.
public class TestPortfolio {

	private PortfolioDao portfolioDao;
	
	@Test
	public void testCreatePortfolio() {
		Portfolio p = new Portfolio();
		StockHolding sh = new StockHolding("GOOGL", 2.0f, 20.0f);
		p.addHolding(sh);
		StockHolding sh2 = new StockHolding("AAPL", 2.0f, 20.0f);
		p.addHolding(sh2);
		
		assertEquals(2235.82f, p.getCurrentValue(), 1.0f);
	}
	
	@Test
	public void testID3() {
		int testId = 3;
		portfolioDao = new PortfolioDaoImpl(dataSource());
		assertEquals(100.0f*145.82f, portfolioDao.getPortfolioById(testId).getCurrentValue(), 1.0f);
	}
	
	public DataSource dataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://sweng500-db.cmvcqoa7soac.us-west-2.rds.amazonaws.com:3306/sweng_500_db");
		dataSource.setUser("dbmasteruser");
		dataSource.setPassword("sweng500*");
		return dataSource;
	}

}
