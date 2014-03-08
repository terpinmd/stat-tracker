package com.hammond.stattracker.service;

import java.util.List;

import android.app.Activity;

import com.hammond.stattracker.dao.PlayerDao;
import com.hammond.stattracker.dao.TeamDao;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;


//TODO MAKE THIS TRANSACTIONAL
public class PlayerService {

	private PlayerDao playerDao;

	private TeamDao teamDao;
	
	public PlayerService(Activity activity) {
		this.playerDao = new PlayerDao(activity);
		this.teamDao = new TeamDao(activity);
	}
	
	public void createDefaults(){
		
		Team team = new Team("MARYLAND");
		teamDao.create(team);
		
		for (int i = 1 ; i < 15 ; i ++){
			Player player = new Player();
			player.setTeam(team);
			player.setJersyNumber(i);
			playerDao.create(player);
		}
	}
	
	
	public List<Player> getAll(){
		return this.playerDao.getAll();
	}
}
