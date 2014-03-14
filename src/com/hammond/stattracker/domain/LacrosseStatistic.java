package com.hammond.stattracker.domain;

public class LacrosseStatistic extends AbstractStatistic{
	
	private static final long serialVersionUID = 600399954835892940L;
	
	public static final String GROUND_BALL = "GROUND_BALL";
	
	@Override
	public String getSport() {
		return "Lacrosse";
	}

}
