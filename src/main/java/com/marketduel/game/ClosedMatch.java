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
		// For each portfolio
		equalizePortfolioValues(portfolio);
	}

	@Override
	void endMatch() {
		// Log values and determine winner
	}

	
}
