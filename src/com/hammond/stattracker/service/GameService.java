package com.hammond.stattracker.service;

import java.util.List;

import android.app.Activity;

import com.hammond.stattracker.dao.GameDao;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.Team;

public class GameService {

	private GameDao gameDao;

	public GameService(Activity activity) {
		this.gameDao = new GameDao(activity);
	}

	public void save(Game game){
		if(game.getId() == null){
			this.gameDao.create(game);			
		}
		else{
			this.gameDao.save(game);	
		}		
	}

	public List<Game> getAll(Team team){
		return this.gameDao.getAll(team);
	}
}
