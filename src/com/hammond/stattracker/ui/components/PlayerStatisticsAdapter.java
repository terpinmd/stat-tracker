package com.hammond.stattracker.ui.components;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;
import com.hammond.stattracker.service.StatisticService;

public class PlayerStatisticsAdapter  extends ArrayAdapter<Player>{

	protected StatisticService statisticService;
	private PlayerService playerService;	
	private Context context;
	public PlayerStatisticsAdapter(Activity activity, Team team){
		super(activity, R.layout.layout_player_detail_row);
		this.playerService = new PlayerService(activity);
		this.statisticService = new StatisticService(activity);
		this.context = activity;
		
		List<Player> players = this.playerService.getPlayersForTeam(team);
		for(Player player : players){
			this.add(player);
		}
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.layout_player_detail_row, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.playerName);
		Player player = (Player)this.getItem(position);
		textView.setText(player.toStringShort());
		
		
		TextView groundBalls = (TextView) rowView.findViewById(R.id.groundBalls);
		groundBalls.setText("10");
		
		TextView assists = (TextView) rowView.findViewById(R.id.assists);
		assists.setText("5");
		
		TextView goals = (TextView) rowView.findViewById(R.id.goals);
		goals.setText("2");
		return rowView;
	}
	
	
}
