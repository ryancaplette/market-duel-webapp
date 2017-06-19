package com.marketduel.game;

import java.util.ArrayList;
import java.util.Date;   // Didn't get much time to research date classes. May make more sense to use something else

public abstract class Game {
	abstract void startGame();
	abstract void endGame();
	
	public static final int MAX_MATCHES_PER_GAME = 10;
	//Shouldnt be needed because these are all things associated with Matches
	//abstract void addPlayer(int plyr);//See below
	//private int initialBalance;
	//protected int[] playerIds;  // A different type of container may make more sense. Made it an array for now.
	
	private int gameId;
	private GameType type;	
	private boolean continuous;
	private int maxPlayersInGame;
	private int playersInGame;
	private Date firstMatchStart;
	private int matchDurationInDays;
	
	private ArrayList<Integer> matchIds;
	
	public Game()
	{
		System.out.println("Creating new game");
		matchIds = new ArrayList<Integer>();
	}
	
	public enum GameType {
	    QUICK, LEAGUE
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
//	public int getInitialBalance() {
//		return initialBalance;
//	}
//	
//	public void setInitialBalance(int initialBalance) {
//		this.initialBalance = initialBalance;
//	}
	
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
	
	public void addMatch(int matchId){
		if (matchIds != null && matchIds.size() < maxPlayersInGame)
		{	
			matchIds.add(matchId);
		}
		else
		{
			//Error: Game is full
		}
	}
	
	public void removePortfolio(int portId)	{
		matchIds.remove(portId);
	}
}
