package com.hammond.stattracker.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;

public class PlayGameActivity extends Activity {

	private PlayerService playerService;
	
	private Team team;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		playerService = new PlayerService(this);
		this.team = (Team) this.getIntent().getSerializableExtra("team");
		// no more this
		// setContentView(R.layout.list_fruit);

		//setListAdapter(new ArrayAdapter<String>(this, R.layout.player_game_row_layout,	getPlayerNames()));

		setContentView(R.layout.activity_play_game);
		
		
		ListView listView = (ListView) findViewById(R.id.gameRosterList);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_dropdown_item, this.getPlayerNames());
		listView.setAdapter(adapter);
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});

	}
	
	private List<String> getPlayerNames(){
		List<String> players = new ArrayList<String>();
		for(Player player : this.playerService.getPlayersForTeam(team)){
			players.add(player.toString());
		}
		return players;
	}
}
