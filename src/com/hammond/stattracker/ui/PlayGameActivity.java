package com.hammond.stattracker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;


public class PlayGameActivity extends Activity {

	private Team team;

	private PlayerService playerService;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        playerService = new PlayerService(this);
        final ListView listview = (ListView) findViewById(R.id.gameRosterList);
        
        this.team = (Team) this.getIntent().getSerializableExtra("team");
        ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this,
                R.layout.player_game_row_layout, R.id.firstNameLabel, playerService.getPlayersForTeam(team));
        listview.setAdapter(adapter);
    }
}
