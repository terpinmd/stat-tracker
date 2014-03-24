package com.hammond.stattracker.domain;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game extends AbstractDomainClass {

	private static final long serialVersionUID = -6757806125352263685L;

	@SuppressLint("SimpleDateFormat")
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	private Team myTeam, vsTeam;

	private String title;

	private Long dateTime;

	private String notes;

	public Game() {
		this.dateTime = new Date().getTime();
	}

	public Team getVsTeam() {
		return vsTeam;
	}

	public void setVsTeam(Team vsTeam) {
		this.vsTeam = vsTeam;
	}

	public Team getMyTeam() {
		return myTeam;
	}

	public void setMyTeam(Team myTeam) {
		this.myTeam = myTeam;
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

	@Override
	public String toString() {
		return "Game from " + DATE_FORMAT.format(getDate());
	}

	public Date getDate(){
		return new Date(this.dateTime);
	}
	
	
}
