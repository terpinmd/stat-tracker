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
		playerDao.create(new Player("Conner", "Hammond", 11, team));
		playerDao.create(new Player("Dylan", "Treese", 10, team));
		playerDao.create(new Player("Cole", "Hammond", 5, team));
	}
	
	
	public List<Player> getAll(){
		return this.playerDao.getAll();
	}
	
	public List<Player> getPlayersForTeam(Team team){
		return this.playerDao.getPlayersForTeam(team);
	}
	
	public void save(Player player){
		if(player.getId() == null){
			this.playerDao.create(player);			
		} else{
			this.playerDao.save(player);	
		}
	}
	
	public void delete(Player player){
		this.playerDao.delete(player);
	}
}
