package com.marketduel.game;
import java.util.Date;

public abstract class Match {
	abstract void startMatch();
	abstract void endMatch();

	private Date startDate;
	private Date endDate;
	private int duration;
	private int maxPlayersInMatch;
	private int playersInMatch;
	
	protected int[] playerIds;
	
	void addPlayer(int playerId)
	{
		if (playersInMatch < maxPlayersInMatch)
		{	
			for (int i = 0; i < getMaxPlayersInMatch(); i++) {
		        if (playerIds[i] == -1)
		        {
		        	playerIds[i] = playerId;
		        	playersInMatch++;
		        	break;
		        }
		    }
		}
		else
		{
			//Error: match is full
		}
	}
	
	void removePlayer(int playerId)	{
		
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
		return playersInMatch;
	}
}
