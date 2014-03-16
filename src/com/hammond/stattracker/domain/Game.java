package com.hammond.stattracker.domain;



public class Game extends AbstractDomainClass{
	
	private static final long serialVersionUID = -6757806125352263685L;

	private Long gameId;
    
    private String title;
    
    private Long dateTime;
    
    private String notes;

	public Long getGameId() {
		return gameId;
	}

	public void setGame(Long gameId) {
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
