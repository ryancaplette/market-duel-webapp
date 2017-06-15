package com.marketduel.game;
import java.util.ArrayList;
import java.util.Date;

public abstract class Match {
	abstract void startMatch();
	abstract void endMatch();

	private Date startDate;
	private Date endDate;
	private int duration;
	private int maxPlayersInMatch;
	private int initialBalance;
	private String winner;
	
	protected ArrayList<Integer> playerIds = new ArrayList<Integer>();
	protected ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
	
	void addPlayer(int playerId)
	{
		if (playerIds.size() < maxPlayersInMatch)
		{	
			playerIds.add(playerId);
		}
		else
		{
			//Error: match is full
		}
	}
	
	void removePlayer(int playerId)	{
		playerIds.remove(playerId);
	}
	
	void setStartDate(Date start) {
		this.startDate = start;
	}
	
	Date getStartDate() {
		return startDate;
	}
	
	void setEndDate(Date end) {
		this.endDate = end;
	}
	
	Date getEndDate() {
		return endDate;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getMaxPlayersInMatch() {
		return maxPlayersInMatch;
	}
	
	public void setMaxPlayersInMatch(int plyrs) {
		this.maxPlayersInMatch = plyrs;
	}
	
	public int getPlayersInMatch() {
		return playerIds.size();
	}
	
	public int getInitialBalance() {
		return initialBalance;
	}
	
	public void setInitialBalance(int initialBalance) {
		this.initialBalance = initialBalance;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}

}
