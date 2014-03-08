package com.hammond.stattracker.domain;

public class Player extends AbstractDomainClass{

	private String firstName, lastName;
	
	private int jersyNumber;
	
	private Team team;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getJersyNumber() {
		return jersyNumber;
	}

	public void setJersyNumber(int jersyNumber) {
		this.jersyNumber = jersyNumber;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Player [firstName=" + firstName + ", lastName=" + lastName
				+ ", jersyNumber=" + jersyNumber + "]";
	}
	
	
	
}
