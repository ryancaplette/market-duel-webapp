package com.marketduel.test;

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
import com.marketduel.dao.PlayerDao;
import com.marketduel.dao.impl.PlayerDaoImpl;
import com.marketduel.model.Player;
import com.mysql.cj.jdbc.MysqlDataSource;

@ContextConfiguration(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
public class TestPlayerDAO {
	
	private PlayerDao playerDao;
	
	@Test
	@Transactional
	public void testAddRemovePlayer() {
		playerDao = new PlayerDaoImpl(dataSource());
		
		Player pl = new Player();
		pl.setUsername("unittest");
		pl.setEmail("ut@ut.com");
		pl.setPassword("testpw");
		
		boolean res = playerDao.registerPlayer(pl);
		
		assert (res);
		
		res = playerDao.deletePlayerbyUsername(pl.getUsername());
		
		assert (res);
	}

	@Test
	public void testGetPlayerbyUsername() {
		playerDao = new PlayerDaoImpl(dataSource());
		
		Player pl = new Player();
		pl.setUsername("unittest");
		pl.setEmail("ut@ut.com");
		pl.setPassword("testpw");
		
		boolean res = playerDao.registerPlayer(pl);
		
		assert (res);
		
		Player retrivedPlayer = playerDao.getPlayerbyUsername(pl.getUsername());
		
		assert (pl.getUsername().compareTo(retrivedPlayer.getUsername()) == 0);
		
		res = playerDao.deletePlayerbyUsername(pl.getUsername());
		
		assert (res);
	}
	
	public DataSource dataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://sweng500-db.cmvcqoa7soac.us-west-2.rds.amazonaws.com:3306/sweng_500_db");
		dataSource.setUser("dbmasteruser");
		dataSource.setPassword("sweng500*");
		return dataSource;
	}
}
