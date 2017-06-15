package com.marketduel.game;

import java.util.Date;
import java.util.ListIterator;

public class ClosedMatch extends Match {

	public ClosedMatch(int plyrs, Date start, int duration) {
		setMaxPlayersInMatch(plyrs);
		setStartDate(start);
		setDuration(duration);
	}

	@Override
	void startMatch() {
		// Set portfolio value equal to initial balance for each portfolio
		ListIterator<Portfolio> itr = portfolios.listIterator();
		while(itr.hasNext()) {
			itr.next().equalizePortfolioValues(getInitialBalance());
		}
	}

	@Override
	void endMatch() {
		// Log values and determine winner
		float[] gains = new float[portfolios.size()];
		
		for (int i=0; i < portfolios.size(); i++)
		{
			gains[0] = portfolios.get(i).getCurrentValue() - portfolios.get(i).getInitialValue();
		}
		
		determineWinner(gains);
	}

	private void determineWinner(float[] gains) {
		float max = gains[0];
		int index = 0;
		int winningPortfolioId = 0;
		
		for (int i=1; i < portfolios.size(); i++)
		{
			if(gains[i] > max)
			{
				max = gains[i];
				index = i;
			}
		}
		
		winningPortfolioId = portfolios.get(index).getPortfolioId();
		
		// DATABASE LOOKUP: find winner name from portfolioId
		// winner = winnerName;
	}

	
}
