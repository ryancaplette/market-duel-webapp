package com.marketduel.game;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.marketduel.model.Player;



public abstract class Match {
	public abstract void startMatch();
	public abstract void endMatch();
	public abstract boolean isTradingActive();
	protected abstract int determineWinner();
	
	public static final int MAX_NUM_PLAYERS = 10;
	
	public enum MatchType {
		Closed, Continuous
	}

	private int matchID;
	private String matchName;
	private Date startDate;
	private Date endDate;
	private Date draftStartDate;
	private Date draftEndDate;
	private int duration;
	private int maxPlayersInMatch;
	private float initialBalance;
	private int winnerId;
	private MatchType matchType;
	
	protected ArrayList<Integer> playerIds = new ArrayList<Integer>();
	protected ArrayList<Integer> portfolioIds = new ArrayList<Integer>();
	
	public void addPlayer(int playerId)
	{
		if (playerId == 0) {
			return;
		}

		if (playerIds.size() < maxPlayersInMatch)
		{
			if (playerIds.contains(playerId)) {
				return; //player has already been added
			}
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
	public ArrayList<Integer> getPlayerIds()
	{
		return playerIds;
	}
	public ArrayList<Integer> getPortfolioIds()
	{
		return portfolioIds;
	}
	protected boolean isDraftActive() {
		Date currentDate = new Date();
		
		if(currentDate.after(getDraftStartDate()) && currentDate.before(getDraftEndDate()))
		{
			return true;
		}
		
		return false;
	}

	protected boolean isMatchActive() {
		Date currentDate = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr = sdf.format(currentDate);
		String matchStartDate = sdf.format(getStartDate());

		if(
				(
					currentDateStr.equals(matchStartDate) ||
					currentDate.after(getStartDate())
				) &&
					currentDate.before(getEndDate())
		)
		{
			return true;
		}

		return false;
	}
	
	public Date getDraftStartDate() {
		return draftStartDate;
	}
	public void setDraftStartDate(Date draftStartDate) {
		this.draftStartDate = draftStartDate;
		//Added this to account for lack of end time or duration in database
		this.setDraftEndDate(draftStartDate);
	}
	public Date getDraftEndDate() {
		return draftEndDate;
	}
	public void setDraftEndDate(Date draftEndDate) {
		//Database doesn't currently store draft end time or duration, so default to 3 hours		
		//this.draftEndDate = draftEndDate;
		this.draftEndDate = addHours(draftEndDate, 3);
	}
	
	private static Date addHours(Date date, int hours) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hours);
				
		return cal.getTime();
	}
	
}
