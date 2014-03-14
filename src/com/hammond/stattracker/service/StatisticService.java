package com.hammond.stattracker.service;

import java.util.List;

import android.app.Activity;

import com.hammond.stattracker.dao.StatisticDao;
import com.hammond.stattracker.domain.AbstractStatistic;
import com.hammond.stattracker.domain.LacrosseStatistic;
import com.hammond.stattracker.domain.Player;


//TODO MAKE THIS TRANSACTIONAL
public class StatisticService {

	
	private StatisticDao statisticDao;
	
	public StatisticService(Activity activity){
		this.statisticDao =  new StatisticDao(activity);
	}
	
	public void groundBall(AbstractStatistic abstractStatistic, int gameId, Player player){
		abstractStatistic.setName(LacrosseStatistic.GROUND_BALL);		
		statisticDao.save(abstractStatistic, gameId, player);

		getGameStatisticsByPlayer(player);
	}
	
	public List<AbstractStatistic> getAllBySport(){
		List<AbstractStatistic> statistics = this.statisticDao.getStatisticsBySport("LACROSSE");
		
		for(AbstractStatistic s : statistics){
			System.out.println(s.getName());
		}
		
		return statistics;
	}
	
	public List<AbstractStatistic> getGameStatisticsByPlayer(Player player){
		List<AbstractStatistic> statistics = this.statisticDao.getGameStatisticsByPlayer(player);
		
		for(AbstractStatistic s : statistics){
			System.out.println(s.getName());
		}
		
		return statistics;		
	}
	
	
}
