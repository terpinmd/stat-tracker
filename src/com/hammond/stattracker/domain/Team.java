package com.hammond.stattracker.domain;

import android.annotation.SuppressLint;

public class Team extends AbstractDomainClass{
	
	private String name;
	
	public Team() {}
	
	public Team(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@SuppressLint("DefaultLocale")
	@Override
	public String toString() {
		return this.name.toUpperCase();
	}	
	
}
