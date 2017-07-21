package com.marketduel.game;

public class ContinuousMatch extends Match {

	public ContinuousMatch() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void startMatch() {
		// TODO Auto-generated method stub

	}

	@Override
	void endMatch() {
		// TODO Auto-generated method stub

	}

	@Override
	protected int determineWinner() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTradingActive() {
		// During a continuous match, trading is open during the draft period and between the match start and end times

		if(this.isDraftActive() || this.isMatchActive())
		{
			return true;
		}
		
		return false;
	}

}
