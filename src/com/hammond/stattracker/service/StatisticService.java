package com.hammond.stattracker.service;

import android.app.Activity;

import com.hammond.stattracker.dao.GameDao;
import com.hammond.stattracker.dao.StatisticDao;
import com.hammond.stattracker.domain.AbstractStatistic;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.Player;


//TODO MAKE THIS TRANSACTIONAL
public class StatisticService {

	
	private StatisticDao statisticDao;
	
	private GameDao gameDao;
	
	public StatisticService(Activity activity){
		this.statisticDao =  new StatisticDao(activity);
		this.gameDao = new GameDao(activity);
	}
	
	public void increment(AbstractStatistic statistic, Game game, Player player){
		if(game.getId() == null)
			this.gameDao.create(game);
		statisticDao.save(statistic, game , player);
		int count = this.statisticDao.getStatisticCount(statistic, player, game);
		statistic.setCount(count);
	}
	
}
