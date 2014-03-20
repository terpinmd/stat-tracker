package com.hammond.stattracker.service;

import android.app.Activity;

import com.hammond.stattracker.dao.GameDao;
import com.hammond.stattracker.domain.Game;



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

}
