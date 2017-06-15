package com.marketduel.game;

import java.util.Arrays;
import java.util.Date;

public class QuickGame extends Game {
	
	// When a Quick Game is created, the game opens immediately for others to join, but the single match
	// doesn't start until the set date
	public QuickGame(int plyrs, Date start, int duration, int balance) {
		setGameId();
		setType(GameType.QUICK);
		setContinuous(false);
		setMaxPlayersInGame(plyrs);
		setFirstMatchStart(start);
		setMatchDurationInDays(duration);
		setInitialBalance(balance);
		initializePlayerIds();
		startGame();
	}

	@Override
	void startGame() {
		matches[0][0] = new ClosedMatch(getMaxPlayersInGame(), getFirstMatchStart(), getMatchDurationInDays());
	}

	@Override
	void endGame() {
		determineWinner(matches[0][0]);
		updateProfiles(matches[0][0]);
	}

	private void updateProfiles(Match match) {
		// TODO Auto-generated method stub
		
	}

	private void determineWinner(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void addPlayer(int plyrId) {
		Date currentDate = new Date();
		if (currentDate.before(getFirstMatchStart()))
		{
			for (int i = 0; i < getMaxPlayersInGame(); i++) {
		        if (playerIds[i] == -1)
		        {
		        	playerIds[i] = plyrId;
		        	matches[0][0].addPlayer(plyrId);  // In a quick game, players are immediately added to the match when added to the game
		        	incrementPlayersInGame();
		        	break;
		        }
		    }
		}
		else
		{
			// Error: match has already started
		}
	}
	
	private void initializePlayerIds() {
		playerIds = new int[getMaxPlayersInGame()];
		Arrays.fill(playerIds, -1);
	}
}