package com.hammond.stattracker.domain;

import android.annotation.SuppressLint;

@SuppressLint("DefaultLocale")
public class Team extends AbstractDomainClass{
	
	private static final long serialVersionUID = -3712956065085724518L;
	
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
	
	
	@Override
	public String toString() {
		return this.name.toUpperCase();
	}	
	
}
