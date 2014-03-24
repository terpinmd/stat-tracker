package com.hammond.stattracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.GameService;

public class GameHistoryActivity extends Activity {

	private GameService gameService;

	private Activity context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.gameService = new GameService(this);
		setContentView(R.layout.activity_game_history);
		context = this;
		
		final ListView listView = (ListView) findViewById(R.id.gameHistoryList);
		Team team = new Team();
		team.setId(Long.valueOf(1));
		listView.setAdapter(new ArrayAdapter<Game>(this, android.R.layout.simple_list_item_1, this.gameService.getAll(team)));
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(context, PlayGameActivity.class);
				intent.putExtra("game", (Game) listView.getSelectedItem());
				startActivity(new Intent(context, PlayGameActivity.class));
			}
			
		});
	}

	public void save(View view) {
		
	}

}
