package com.hammond.stattracker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.ui.components.PlayerSpinnerAdapter;


public class PlayGameActivity extends Activity {

	private Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        
        final ListView listview = (ListView) findViewById(R.id.gameRosterList);
        
        this.team = (Team) this.getIntent().getSerializableExtra("team");
        PlayerSpinnerAdapter adapter = new PlayerSpinnerAdapter(this, team);
        listview.setAdapter(adapter);
    }

    
}
