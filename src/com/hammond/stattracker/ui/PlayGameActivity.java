package com.hammond.stattracker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.GameService;
import com.hammond.stattracker.ui.components.PlayerStatisticsAdapter;

public class PlayGameActivity extends Activity {

	
	private GameService gameService;
	
	private Game game;
	
	private Team team;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.gameService = new GameService(this);
		
		setContentView(R.layout.activity_play_game);	
		
		this.team = (Team) this.getIntent().getSerializableExtra("team");
		this.game = (Game) this.getIntent().getSerializableExtra("game");			
		
		ListView listView = (ListView) findViewById(R.id.gameRosterList);
		ArrayAdapter<Player> adapter = new PlayerStatisticsAdapter(this, game, team);
		listView.setAdapter(adapter);
		listView.setTextFilterEnabled(true);
	}
	
	
	public void save(View view){
		gameService.save(this.game);
	}

}
