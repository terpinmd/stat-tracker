package com.hammond.stattracker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;
import com.hammond.stattracker.ui.components.PlayerGameListAdapter;


public class PlayGameActivity extends Activity {

	private Team team;

	protected PlayerService playerService;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        playerService = new PlayerService(this);
        final ListView listview = (ListView) findViewById(R.id.gameRosterList);
        
        this.team = (Team) this.getIntent().getSerializableExtra("team");
        PlayerGameListAdapter adapter = new PlayerGameListAdapter(this, this.team);
        listview.setAdapter(adapter);
    }
}
