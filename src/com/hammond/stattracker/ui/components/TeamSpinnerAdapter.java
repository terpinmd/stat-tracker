package com.hammond.stattracker.ui.components;

import java.util.List;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.TeamService;

public class TeamSpinnerAdapter extends ArrayAdapter<Team>{

	private TeamService teamService;

	private List<Team> teams;
	
	
	public TeamSpinnerAdapter(Activity activity) {
		super(activity,  android.R.layout.simple_spinner_dropdown_item);
		teamService = new TeamService(activity);
		teams = this.teamService.getAll();
		for(Team team : teams){
			this.add(team);
		}
		this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}
	
}
