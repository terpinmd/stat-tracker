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
import com.hammond.stattracker.service.PlayerService;
import com.hammond.stattracker.service.StatisticService;

public class PlayerStatisticsAdapter  extends ArrayAdapter<Player>{

	protected StatisticService statisticService;
	private PlayerService playerService;
	private Activity context;
	private Game game;
	
	public PlayerStatisticsAdapter(Activity activity, Game game){ //Isnt team in game?
		super(activity, R.layout.layout_player_detail_row);
		this.playerService = new PlayerService(activity);
		this.statisticService = new StatisticService(activity);
		this.context = activity;
		this.game = game;
				
		//Might be better of to make this a query that returns each player and stats for the game
		List<Player> players = this.playerService.getPlayersForTeam(game.getMyTeam());
		for(Player player : players){
			this.add(player);
		}
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.layout_player_detail_row, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.playerName);
		final Player player = (Player)this.getItem(position);
		textView.setText(player.toStringShort());
		
		final TextView groundBalls = (TextView) rowView.findViewById(R.id.groundBalls);
		groundBalls.setText(this.statisticService.count(this.getLacrosseStatisticGb(), game, player).toString());
		groundBalls.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				AbstractStatistic statistic = getLacrosseStatisticGb();
				statisticService.increment(statistic, game, player);
				groundBalls.setText(statistic.getCount().toString());
			}			
		});
		
		
		final TextView assists = (TextView) rowView.findViewById(R.id.assists);
		assists.setText(this.statisticService.count(this.getLacrosseStatisticAssist(), game, player).toString());
		assists.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				AbstractStatistic statistic = getLacrosseStatisticAssist();
				statisticService.increment(statistic, game, player);
				assists.setText(statistic.getCount().toString());
			}			
		});
		
		final TextView goals = (TextView) rowView.findViewById(R.id.goals);
		goals.setText(this.statisticService.count(this.getLacrosseStatisticGoal(), game, player).toString());
		goals.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				AbstractStatistic statistic = getLacrosseStatisticGoal();
				statisticService.increment(statistic, game, player);
				goals.setText(statistic.getCount().toString());
			}			
		});
		return rowView;
	}
	
	private AbstractStatistic getLacrosseStatisticGb(){
		AbstractStatistic statistic = new LacrosseStatistic("GROUND_BALL");
		statistic.setId(Long.valueOf(1));
		return statistic;
	}

	private AbstractStatistic getLacrosseStatisticAssist(){
		AbstractStatistic statistic = new LacrosseStatistic("ASSIST");
		statistic.setId(Long.valueOf(2));
		return statistic;
	}
	
	private AbstractStatistic getLacrosseStatisticGoal(){
		AbstractStatistic statistic = new LacrosseStatistic("GOAL");
		statistic.setId(Long.valueOf(3));
		return statistic;
	}
	
}
