package com.hammond.stattracker.ui.components;

import java.util.List;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;

public class PlayerSpinnerAdapter extends ArrayAdapter<Player>{

	private PlayerService playerService;

	private List<Player> players;
	
	
	public PlayerSpinnerAdapter(Activity activity, Team team) {
		super(activity,  android.R.layout.simple_spinner_dropdown_item);
		playerService = new PlayerService(activity);
		if(team != null){
			players = this.playerService.getPlayersForTeam(team);
			for(Player player : players){
				this.add(player);
			}
		}
		this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}
	
}
