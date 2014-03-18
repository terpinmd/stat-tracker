package com.hammond.stattracker.domain;

public class Player extends AbstractDomainClass{

	private static final long serialVersionUID = -7889391051781056596L;

	private String firstName, lastName;
	
	private Integer jersyNumber;
	
	private Team team;

		
	public Player(){}
	
	public Player(String firstName, String lastName, Integer jersyNumber,
			Team team) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.jersyNumber = jersyNumber;
		this.team = team;
	}

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

	public Integer getJersyNumber() {
		return jersyNumber;
	}

	public void setJersyNumber(Integer jersyNumber) {
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
		return "#" + this.getJersyNumber() + ": " + this.getFirstName() + " " + this.getLastName();
	}
	
	public String toStringShort() {
		return "#" + this.getJersyNumber() + ": " + this.getLastName();
	}
	
}
