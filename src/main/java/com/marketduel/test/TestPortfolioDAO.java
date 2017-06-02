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
public class TestPortfolioDAO {
	@Test
	@Rollback(true)
	public void testCreatePortfolio() {
		assert (false);
	}

	@Test
	@Rollback(true)
	public void testAddStockToPortfolio() {
		assert (false);
	}
	
	@Test
	@Rollback(true)
	public void testGetPortfoliosByUsername() {
		assert (false);
	}
}
