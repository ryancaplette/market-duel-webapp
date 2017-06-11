package com.marketduel.game;

import java.util.Date;

public class ClosedMatch extends Match {

	public ClosedMatch(int plyrs, Date start, int duration) {
		setMaxPlayersInMatch(plyrs);
		setStartDate(start);
		setDuration(duration);
	}

	@Override
	void startMatch() {
		// Set portfolio value equal to balance
		equalizePortfolioValues();
	}

	@Override
	void endMatch() {
		// Log values and determine winner
	}

	/* Since players may add and remove stocks between the game start and the match start, the value of each portfolio
	 * shall be adjusted to match the balance set for the match. This is done by scaling the number of shares of each
	 * stock in the portfolio at the time of the start of the match so that each portfolio value equals the set balance. 
	 */
	private void equalizePortfolioValues() {
				
	}
}
