package com.marketduel.game;

import java.util.Date;

import com.marketduel.game.Match.MatchType;
import com.marketduel.model.Player;

public class QuickGame extends Game {

	private Match match;

	// When a Quick Game is created, the game opens immediately for others to join, but the single match
	// doesn't start until the set date
	public QuickGame(int plyrs, Date start, int duration, int balance) {
		setType(GameType.QUICK);
		setContinuous(false);
		setMaxPlayersInGame(plyrs);
		setFirstMatchStart(start);
		setMatchDurationInDays(duration);
		startGame(balance);
	}

	public QuickGame() {
		setType(GameType.QUICK);
	}

	@Override
	void startGame(int balance) {
		this.match = new ClosedMatch(getMaxPlayersInGame(), getFirstMatchStart(), getMatchDurationInDays(), MatchType.Closed, balance);
	}

	public Match getMatch() {
		return this.match;
	}

	@Override
	void endGame() {
//		determineWinner(matches[0][0]);
//		updateProfiles(matches[0][0]);
	}

	private void updateProfiles(Match match) {
		// TODO Auto-generated method stub
		
	}

	private void determineWinner(Match match) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getPlayersInGame() {
		return this.getFirstMatch().getPlayersInMatch();
	}
	
	@Override
	public int getMaxPlayersInGame() {
		return this.getFirstMatch().getMaxPlayersInMatch();
	}
}
