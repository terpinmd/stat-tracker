package com.hammond.stattracker.service;

import java.util.List;

import android.app.Activity;

import com.hammond.stattracker.dao.TeamDao;
import com.hammond.stattracker.domain.Team;


//TODO MAKE THIS TRANSACTIONAL
public class TeamService {
	private TeamDao teamDao;
	
	public TeamService(Activity activity) {
		this.teamDao = new TeamDao(activity);
	}
	
	public void createDefaults(){		
		Team team = new Team("MARYLAND");
		teamDao.create(team);
	}
	
	public List<Team> getAll(){
		return this.teamDao.getAll();
	}
	
}
