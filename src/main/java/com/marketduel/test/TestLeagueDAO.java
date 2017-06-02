package com.marketduel.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.marketduel.App;

@ContextConfiguration(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestLeagueDAO {
	@Test
	@Rollback(true)
	public void testCreateLeague() {
		assert (false);
	}
	
	@Test
	@Rollback(true)
	public void testAddGameToLeague() {
		assert (false);
	}
	
	@Test
	@Rollback(true)
	public void testChangeLeaguePrivacy() {
		assert (false);
	}

	@Test
	@Rollback(true)
	public void testGetLeagueGames() {
		assert (false);
	}
	
	@Test
	@Rollback(true)
	public void testGetLeaguesForPlayer() {
		assert (false);
	}
}
