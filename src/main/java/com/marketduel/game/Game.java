package com.marketduel.game;

import java.util.*;

import com.marketduel.dao.impl.MatchDaoImpl;
import com.marketduel.dao.impl.PortfolioDaoImpl;
import com.marketduel.config.DatabaseConfig;

public abstract class Game {
	abstract void startGame(int balance);
	abstract void endGame();
	
	public static final int MAX_MATCHES_PER_GAME = 10;
	//private int initialBalance;
	
	private int gameId;
	private GameType type;	
	private boolean continuous;
	private Date firstMatchStart;
	private int matchDurationInDays;
	
	protected int NumMatches;
	protected ArrayList<Integer> matchIds = new ArrayList<Integer>();
	
	private int maxPlayersInGame;
	private int curPlayersInGame;
	protected ArrayList<Integer> playerIds = new ArrayList<Integer>();

	protected String gameName;
	
	public ArrayList<Integer> getMatchIds() {
		return matchIds;
	}
	public Game()
	{
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

	public void setCurPlayersInGame(int curPlayersInGame) {
		this.curPlayersInGame = curPlayersInGame;
	}

	public int getPlayersInGame() {
		return curPlayersInGame;
	}
	
	public ArrayList<Integer> getPlayerIds() {
		return playerIds;
	}
	
	public void incrementPlayersInGame() {
		this.curPlayersInGame += 1;
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

		// Adding Match to game object
		if (matchIds.size() < MAX_MATCHES_PER_GAME) {
			System.out.println("adding matchId " + matchId);
			matchIds.add(matchId);
		} else {
			System.out.println("Error: matchId's are full, cannot add more.");
		}

	}
	
	public Date getGameEndDate()
	{
		Date endDate;
		int matches, gameDays;
		
		matches = matchIds.size();		
		gameDays = matches*matchDurationInDays;		
		endDate = addDays(firstMatchStart, gameDays);
		
		return endDate;
	}
	
	public void removePortfolio(int portId)	{
		matchIds.remove(portId);
	}
	
	private static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}
	
	public boolean addPlayer(int playerId) {
		Date currentDate = new Date();
		
		//If Draft Game and date is after draft end then dont let a player join
		if (getContinuous() == false && currentDate.after(getFirstMatchDraftDate())) return false;
		
		if (currentDate.before(firstMatchStart))
		{
			if (curPlayersInGame < maxPlayersInGame)
			{
				playerIds.add(playerId);
				curPlayersInGame++;
				return true;
			}
		}

		return false;
	}

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    
    public Date getFirstMatchDraftDate() {
    	DatabaseConfig d = new DatabaseConfig();
		MatchDaoImpl m = new MatchDaoImpl(d.dataSource());

    	Match firstMatch = m.getMatchById(matchIds.get(0));
    	return firstMatch.getDraftStartDate();
    }
    
	public Match getFirstMatch() {
		DatabaseConfig d = new DatabaseConfig();
		MatchDaoImpl m = new MatchDaoImpl(d.dataSource());

    	return m.getMatchById(matchIds.get(0));
	}
}
