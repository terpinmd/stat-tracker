package com.hammond.stattracker.service;

import android.app.Activity;

import com.hammond.stattracker.dao.StatisticDao;
import com.hammond.stattracker.domain.AbstractStatistic;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.Player;


//TODO MAKE THIS TRANSACTIONAL
public class StatisticService {

	
	private StatisticDao statisticDao;
	
	public StatisticService(Activity activity){
		this.statisticDao =  new StatisticDao(activity);
	}
	
	public void increment(AbstractStatistic statistic, Game game, Player player){
		statisticDao.save(statistic, game , player);
		Integer count = count(statistic, game , player);
		statistic.setCount(count);
	}
	
	public void decrement(AbstractStatistic statistic, Game game, Player player){
		this.statisticDao.deleteLast(statistic, player, game);
		Integer count = count(statistic, game , player);
		statistic.setCount(count);
	}
	
	public Integer count(AbstractStatistic statistic, Game game, Player player){
		return this.statisticDao.getStatisticCount(statistic, player, game);
	}
	
}
