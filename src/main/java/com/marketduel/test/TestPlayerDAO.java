package com.marketduel.test;

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

@ContextConfiguration(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPlayerDAO {
	@Test
	@Rollback(true)
	public void testRegisterPlayer() {
		assert (false);
	}

	@Test
	@Rollback(true)
	public void testGetPlayerbyUsername() {
		assert (false);
	}
}
