package com.marketduel.game;

import java.util.Date;   // Didn't get much time to research date classes. May make more sense to use something else

public abstract class Game {
	abstract void startGame();
	abstract void endGame();
	abstract void addPlayer(int plyr);
	
	private int gameId;
	private GameType type;	
	private boolean continuous;
	private int maxPlayersInGame;
	private int playersInGame;
	private Date firstMatchStart;
	private int matchDurationInDays;
	private int initialBalance;
	
	protected int[] playerIds;  // A different type of container may make more sense. Made it an array for now.
	protected Match[][] matches;  // All matches in the game by round and match number
	
	protected enum GameType {
	    QUICK, LEAGUE
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setGameId() {
		this.gameId = getNextGameId();
	}
	
	private int getNextGameId()	{
		return 0;  // Update with code to return new game ID based on next available number in the database
	}
	
	public int getInitialBalance() {
		return initialBalance;
	}
	
	public void setInitialBalance(int initialBalance) {
		this.initialBalance = initialBalance;
	}
	
	public int getMaxPlayersInGame() {
		return maxPlayersInGame;
	}
	
	public void setMaxPlayersInGame(int maxPlayersInGame) {
		this.maxPlayersInGame = maxPlayersInGame;
	}
	
	public int getPlayersInGame() {
		return playersInGame;
	}
	
	public void incrementPlayersInGame() {
		this.playersInGame += 1;
	}
	
	public GameType getType() {
		return type;
	}
	
	public void setType(GameType type) {
		this.type = type;
	}
	
	public Date getFirstMatchStart() {
		return firstMatchStart;
	}
	
	public void setFirstMatchStart(Date firstMatchStart) {
		this.firstMatchStart = firstMatchStart;
	}
	
	public int getMatchDurationInDays() {
		return matchDurationInDays;
	}
	
	public void setMatchDurationInDays(int matchDurationInDays) {
		this.matchDurationInDays = matchDurationInDays;
	}
	
	public boolean getContinuous() {
		return continuous;
	}
	
	public void setContinuous(boolean continuous){
		this.continuous = continuous;
	}
}
