package com.marketduel.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.marketduel.App;
import com.marketduel.game.*;

public class TestSingleMatchQuickPlay {
	@Test
	public void testCreateSingleMatchQuickPlayGame() {
		//Creates game and checks that all the required data for the game exists
		
		final int NUM_OF_PLAYERS = 8;
		final int DURATION = 5;
		final int BALANCE = 30000;
		
		// Make start date tomorrow
		Date currentDate = Calendar.getInstance().getTime();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		cal.add(Calendar.DATE, 1);
		Date startDate = cal.getTime();
		
		// Create new game with NUM_OF_PLAYERS players, a start date of tomorrow, a duration of DURATION days, and a balance of BALANCE
		QuickGame g = new QuickGame(NUM_OF_PLAYERS, startDate, DURATION, BALANCE);
		
		// Check player configured parameters
		assertTrue("Checking allowed number of players", g.getMaxPlayersInGame() == NUM_OF_PLAYERS);
		assertTrue("Checking start date", g.getFirstMatchStart() == startDate);
		assertTrue("Checking duration", g.getMatchDurationInDays() == DURATION);
		assertTrue("Checking initial balance", g.getMatch().getInitialBalance() == BALANCE);
		
		// Check single quick game definition
		assertTrue("Checking that matches do not allow trading", g.getContinuous() == false);
		assertTrue("Checking that game is quick type", g.getType() == Game.GameType.QUICK);
		
		// Check that NUM_OF_PLAYERS can be added
		for(int i=1; i <= NUM_OF_PLAYERS; i++)
		{
			g.addPlayer(i);
		}
		
		ArrayList<Integer> playerIds = g.getPlayerIds();
		
		for(int i=1; i <= NUM_OF_PLAYERS; i++)
		{
			assertTrue("Checking player ID", playerIds.get(i-1) == i);
		}
	}
	
	@Test
	public void testAdd8Players() {
		final int NUM_OF_PLAYERS = 8;
		//Add players to the game manually
		//Check that the expected number of players were added
		assertEquals("Expected number of players not added", NUM_OF_PLAYERS, 0);
	}
	
	@Test
	public void testAddPortfolios() {
		//Add stocks to each of the players' portfolios
		//Check that the stocks were added to each portfolio as expected
		assertTrue("Error adding initial stocks to portfolios", false);
	}
	
	@Test
	public void testStartSingleMatchQuickPlayGame() {
		//Set date/time to start date/time
		//Check that portfolios were updated correctly (e.g. purchased stock value equals initial balance)
		assertTrue("Portfolio value does not equal initial balance", false);
		//Check that players cannot change their portfolios		
		assertTrue("Stock holdings may not be modified", false);
	}
	
	@Test
	public void testPortfolioValueUpdate() {
		//Set date/time to between start and end date/time
		//Check that portfolio values are updated correctly based on current stock data
		assertTrue("Portfolio value must reflect current data", false);
	}
	
	@Test
	public void testShowStandings() {
		//Check that players are shown in order of their portfolio's net worth
		assertTrue("Error displaying standings", false);
	}
	
	@Test
	public void testEndSingleMatchQuickPlayGame() {
		//Set date/time to end date/time
		//Check that final stock values have been stored
		assertTrue("Error storing end stock data", false);
		//Check that portfolio values have been calculated correctly
		assertTrue("Error calcuating final portfolio values", false);
	}
	
	@Test
	public void testDetermineWinner() {
		//Check that the correct player is determined to be the winner
		assertTrue("Error determining correct winner", false);
		//Check that player records have been updated correctly
		assertTrue("Error updating player records", false);
	}
}
