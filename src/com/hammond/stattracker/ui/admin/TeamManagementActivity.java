package com.hammond.stattracker.ui.admin;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;
import com.hammond.stattracker.service.TeamService;


public class TeamManagementActivity extends Activity {

    protected TeamService teamService;
	
    protected PlayerService playerService;
    
    private Spinner teamSpinner;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamService = new TeamService(this);
        playerService = new PlayerService(this);
        //playerService.createDefaults();
        setContentView(R.layout.activity_team_mgmt); 
        populateTeams();
    }
    
    
    public void populateTeams(){
    	final Activity source = this;
    	teamSpinner = (Spinner) findViewById(R.id.select_team_spinner);
    	List<Team> list = this.teamService.getAll();
       	ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this, android.R.layout.simple_spinner_dropdown_item, list);
       	
    	teamSpinner.setAdapter(adapter);
    	teamSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view, int position, long rowId) {
				Team team = (Team) teamSpinner.getItemAtPosition(position);				
				
				TextView tv = (TextView) findViewById(R.id.team_management_name_text);
				tv.setText("Team: " + team.getName());
				
				//ListView playerListView = (ListView) findViewById(R.id.team_player_list_view);
				List<Player> playersByTeam = playerService.getAll();
				for(Player p : playersByTeam){
					System.out.println(p);
				}
				/*ArrayAdapter<Player> playerAdapter = new ArrayAdapter<Player>(source, android.R.layout.list_content, playersByTeam);
				playerListView.setAdapter(playerAdapter);*/
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
    	});
    }

}
 