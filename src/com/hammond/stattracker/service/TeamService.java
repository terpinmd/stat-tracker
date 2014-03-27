package com.hammond.stattracker.service;

import java.util.Collections;
import java.util.Comparator;
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
		List<Team> teams =  this.teamDao.getAll();
		Collections.sort(teams, new Comparator<Team>(){
			@Override
			public int compare(Team first, Team second) {
				return first.getName().compareTo(second.getName());
			}
			
		});
		return teams;
	}
	
	public void save(Team team){
		if(team.getId() == null){
			this.teamDao.create(team);
		} else{
			this.teamDao.save(team);
		}		
	}

	public void delete(Team team) {
		teamDao.delete(team);
	}
}
