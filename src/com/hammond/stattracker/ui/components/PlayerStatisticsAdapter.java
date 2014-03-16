package com.hammond.stattracker.ui.components;

import java.util.List;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;
import com.hammond.stattracker.service.StatisticService;

public class PlayerStatisticsAdapter  extends ArrayAdapter<Player>{

	protected StatisticService statisticService;
	private PlayerService playerService;	
	public PlayerStatisticsAdapter(Activity activity, Team team){
		super(activity, android.R.layout.simple_spinner_dropdown_item);
		this.playerService = new PlayerService(activity);
		this.statisticService = new StatisticService(activity);
		List<Player> players = this.playerService.getPlayersForTeam(team);
		for(Player player : players){
			this.add(player);
		}
	}
}
