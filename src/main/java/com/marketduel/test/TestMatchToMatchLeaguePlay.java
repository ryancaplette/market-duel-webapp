package com.marketduel.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.marketduel.App;

public class TestMatchToMatchLeaguePlay {
	@Test
	public void testCreateMatchToMatchLeaguePlayGame() {
		//Creates game
		//Check that all the required data for the game exists
		assertTrue("New match to match league play game not created", false);
		assertTrue(false);
	}
	
	@Test
	public void testAdd8Players() {
		final int NUM_OF_PLAYERS = 8;
		//Add players to the game manually
		//Check that the expected number of players were added
		assertEquals("Expected number of players not added", NUM_OF_PLAYERS, 0);
	}
	
	@Test
	public void testAddPortfolios1() {
		//Add stocks to each of the players' portfolios
		//Check that the stocks were added to each portfolio as expected
		assertTrue("Error adding initial stocks to portfolios", false);
	}
	
	@Test
	public void testStartMatchToMatchLeaguePlayMatch1() {
		//Set date/time to start date/time
		//Check that portfolios were updated correctly (e.g. purchased stock value equals initial balance)
		assertTrue("Portfolio value does not equal initial balance", false);
		//Check that players cannot change their portfolios		
		assertTrue("Stock holdings may not be modified", false);
	}
	
	@Test
	public void testPortfolioValueUpdate1() {
		//Set date/time to between start and end date/time
		//Check that portfolio values are updated correctly based on current stock data
		assertTrue("Portfolio value must reflect current data", false);
	}
	
	@Test
	public void testShowStandings1() {
		//Check that players are shown in order of their portfolio's net worth
		assertTrue("Error displaying standings", false);
	}
	
	@Test
	public void testEndMatchToMatchLeaguePlayMatch1() {
		//Set date/time to end date/time
		//Check that final stock values have been stored
		assertTrue("Error storing end stock data", false);
		//Check that portfolio values have been calculated correctly
		assertTrue("Error calcuating final portfolio values", false);
	}
	
	@Test
	public void testDetermineMatchWinner1() {
		//Check that the correct player is determined to be the winner
		assertTrue("Error determining correct winner", false);
		//Check that player records have been updated correctly
		assertTrue("Error updating player records", false);
	}
	
	@Test
	public void testAddPortfolios2() {
		//Add stocks to each of the players' portfolios
		//Check that the stocks were added to each portfolio as expected
		assertTrue("Error adding initial stocks to portfolios", false);
	}
	
	@Test
	public void testStartMatchToMatchLeaguePlayMatch2() {
		//Set date/time to start date/time
		//Check that portfolios were updated correctly (e.g. purchased stock value equals initial balance)
		assertTrue("Portfolio value does not equal initial balance", false);
		//Check that players cannot change their portfolios		
		assertTrue("Stock holdings may not be modified", false);
	}
	
	@Test
	public void testPortfolioValueUpdate2() {
		//Set date/time to between start and end date/time
		//Check that portfolio values are updated correctly based on current stock data
		assertTrue("Portfolio value must reflect current data", false);
	}
	
	@Test
	public void testShowStandings2() {
		//Check that players are shown in order of their portfolio's net worth
		assertTrue("Error displaying standings", false);
	}
	
	@Test
	public void testEndMatchToMatchLeaguePlayMatch2() {
		//Set date/time to end date/time
		//Check that final stock values have been stored
		assertTrue("Error storing end stock data", false);
		//Check that portfolio values have been calculated correctly
		assertTrue("Error calcuating final portfolio values", false);
	}
	
	@Test
	public void testDetermineMatchWinner2() {
		//Check that the correct player is determined to be the winner
		assertTrue("Error determining correct winner", false);
		//Check that player records have been updated correctly
		assertTrue("Error updating player records", false);
	}
	
	public void testDetermineLeagueWinner() {
		//Check that the correct player is determined to be the winner
		assertTrue("Error determining correct winner", false);
		//Check that player records have been updated correctly
		assertTrue("Error updating player records", false);
	}
}

