package com.hammond.stattracker.ui.components;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.AbstractStatistic;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.LacrosseStatistic;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;
import com.hammond.stattracker.service.StatisticService;

public class PlayerStatisticsAdapter  extends ArrayAdapter<Player>{

	protected StatisticService statisticService;
	private PlayerService playerService;	
	private Context context;
	private Game game;
	
	public PlayerStatisticsAdapter(Activity activity, Game game, Team team){ //Isnt team in game?
		super(activity, R.layout.layout_player_detail_row);
		this.playerService = new PlayerService(activity);
		this.statisticService = new StatisticService(activity);
		this.context = activity;
		this.game = game;
		
		
		//Might be better of to make this a query that returns each player and stats for the game
		List<Player> players = this.playerService.getPlayersForTeam(team);
		for(Player player : players){
			this.add(player);
		}
	}
	
	//Might need to build the query into this roster
	//http://stackoverflow.com/questions/3669325/notifydatasetchanged-example/5092426#5092426
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.layout_player_detail_row, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.playerName);
		final Player player = (Player)this.getItem(position);
		textView.setText(player.toStringShort());
		
	
		final TextView groundBalls = (TextView) rowView.findViewById(R.id.groundBalls);
		groundBalls.setText("0");
		groundBalls.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				AbstractStatistic statistic = new LacrosseStatistic("GROUND_BALL");
				statistic.setId(Long.valueOf(1));
				statisticService.increment(statistic, game, player);
				groundBalls.setText(statistic.getCount().toString());
			}			
		});
		
		
		final TextView assists = (TextView) rowView.findViewById(R.id.assists);
		assists.setText("0");
		assists.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				AbstractStatistic statistic = new LacrosseStatistic("ASSIST");
				statistic.setId(Long.valueOf(2));
				statisticService.increment(statistic, game, player);
				assists.setText(statistic.getCount().toString());
			}			
		});
		
		final TextView goals = (TextView) rowView.findViewById(R.id.goals);
		goals.setText("0");
		goals.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				AbstractStatistic statistic = new LacrosseStatistic("GOAL");
				statistic.setId(Long.valueOf(3));
				statisticService.increment(statistic, game, player);
				goals.setText(statistic.getCount().toString());
			}			
		});
		return rowView;
	}
	
}
