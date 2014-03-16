package com.hammond.stattracker.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.LacrosseStatistic;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.StatisticService;
import com.hammond.stattracker.ui.components.PlayerStatisticsAdapter;

public class PlayGameActivity extends Activity {

	
	private StatisticService statisticService;

	private Context context = this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);	
		
		statisticService = new StatisticService(this);
		final Team team = (Team) this.getIntent().getSerializableExtra("team");
		final Game game = (Game) this.getIntent().getSerializableExtra("game");			
		
		final ListView listView = (ListView) findViewById(R.id.gameRosterList);
		ArrayAdapter<Player> adapter = new PlayerStatisticsAdapter(this,  team);
		listView.setAdapter(adapter);
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.dialog_player_statistic);
				dialog.setTitle("Track");
				dialog.show();
				
				final Player player = (Player) listView.getItemAtPosition(position);
				
				((Button) dialog.findViewById(R.id.groundBall)).setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View view) {
						statisticService.save(new LacrosseStatistic("GROUND_BALL"), game, player);
					}			
				});
				
				((Button) dialog.findViewById(R.id.assist)).setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View view) {
						statisticService.save(new LacrosseStatistic("ASSIST"), game, player);
					}			
				});
				
				((Button) dialog.findViewById(R.id.goal)).setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View view) {
						statisticService.save(new LacrosseStatistic("GOAL"), game, player);
					}			
				});
			}
		});

	}

}
