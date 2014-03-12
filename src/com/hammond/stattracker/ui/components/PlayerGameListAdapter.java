package com.hammond.stattracker.ui.components;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;

public class PlayerGameListAdapter extends ArrayAdapter<Player>{

	private PlayerService playerService;

	private List<Player> players;
	
	
	public PlayerGameListAdapter(Activity activity, Team team) {
		super(activity,  android.R.layout.simple_spinner_dropdown_item);
		playerService = new PlayerService(activity);
		players = this.playerService.getPlayersForTeam(team);
		for(Player player : players){
			this.add(player);
		}
		this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}
	
	
	
	
}
