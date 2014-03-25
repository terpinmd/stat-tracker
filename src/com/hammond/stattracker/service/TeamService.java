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
	
	public List<Team> getAll(){
		return this.teamDao.getAll();
	}
	
	public void save(Team team){
		if(team.getId() == null){
			this.teamDao.create(team);
		} else{
			this.teamDao.save(team);
		}		
	}
}
