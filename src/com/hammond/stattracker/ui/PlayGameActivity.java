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
import com.hammond.stattracker.service.PlayerService;
import com.hammond.stattracker.service.StatisticService;

public class PlayGameActivity extends Activity {

	private PlayerService playerService;
	
	private Team team;
	
	private Game game;
	
	private StatisticService statisticService;

	private Context context = this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		playerService = new PlayerService(this);
		statisticService = new StatisticService(this);
		this.team = (Team) this.getIntent().getSerializableExtra("team");
		this.game = (Game) this.getIntent().getSerializableExtra("game");
		// no more this
		// setContentView(R.layout.list_fruit);

		//setListAdapter(new ArrayAdapter<String>(this, R.layout.player_game_row_layout,	getPlayerNames()));

		setContentView(R.layout.activity_play_game);
		
		
		final ListView listView = (ListView) findViewById(R.id.gameRosterList);
		ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this,  android.R.layout.simple_spinner_dropdown_item, this.playerService.getPlayersForTeam(team));
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
						System.out.println("clickked!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
						statisticService.groundBall(new LacrosseStatistic(), game, player);
					}			
				});
			}
		});

		
		
		this.statisticService.getAllBySport();
	}

}
