package com.marketduel.game;
import java.util.ArrayList;
import java.util.Date;

import com.marketduel.model.Player;

enum MatchType {
	Closed, Continuous
}

public abstract class Match {
	abstract void startMatch();
	abstract void endMatch();
	
	public static final int MAX_NUM_PLAYERS = 10;

	private int matchID;
	private String matchName;
	private Date startDate;
	private Date endDate;
	private int duration;
	private int maxPlayersInMatch;
	private float initialBalance;
	private int winnerId;
	private MatchType matchType;
	
	protected ArrayList<Integer> playerIds = new ArrayList<Integer>();
	protected ArrayList<Integer> portfolioIds = new ArrayList<Integer>();
	
	public void addPlayer(int playerId)
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
	
	public void removePlayer(int playerId)	{
		playerIds.remove(playerId);
	}
	
	public void addPortfolio(int portId)
	{
		if (portfolioIds.size() < maxPlayersInMatch)
		{	
			portfolioIds.add(portId);
		}
		else
		{
			//Error: match is full
		}
	}
	
	public void removePortfolio(int portId)	{
		portfolioIds.remove(portId);
	}
	
	public void setStartDate(Date start) {
		this.startDate = start;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setEndDate(Date end) {
		this.endDate = end;
	}
	
	public Date getEndDate() {
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
	
	public float getInitialBalance() {
		return initialBalance;
	}
	
	public void setInitialBalance(float f) {
		this.initialBalance = f;
	}
	public int getWinnerId() {
		return winnerId;
	}
	public void setWinnerId(int winnerId) {
		this.winnerId = winnerId;
	}
	public int getMatchID() {
		return matchID;
	}
	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}
	public String getMatchName() {
		return matchName;
	}
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	public MatchType getMatchType() {
		return matchType;
	}
	public void setMatchType(MatchType matchType) {
		this.matchType = matchType;
	}
}
