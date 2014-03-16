package com.hammond.stattracker.domain;

import java.util.Date;




public class Game extends AbstractDomainClass{
	
	private static final long serialVersionUID = -6757806125352263685L;

	private Long gameId, myTeamId, vsTeamId;
    
    private String title;
    
    private Long dateTime;
    
    private String notes;

    public Game(){
    	this.dateTime = new Date().getTime();
    }
    
	public Long getGameId() {
		return gameId;
	}

	public void setGame(Long gameId) {
		this.gameId = gameId;
	}

	
	public Long getMyTeamId() {
		return myTeamId;
	}

	public void setMyTeamId(Long myTeamId) {
		this.myTeamId = myTeamId;
	}

	public Long getVsTeamId() {
		return vsTeamId;
	}

	public void setVsTeamId(Long vsTeamId) {
		this.vsTeamId = vsTeamId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getDateTime() {
		return dateTime;
	}

	public void setDateTime(Long dateTime) {
		this.dateTime = dateTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}   

	
}
