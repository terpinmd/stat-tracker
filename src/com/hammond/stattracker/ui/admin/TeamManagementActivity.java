package com.hammond.stattracker.ui.admin;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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
        setContentView(R.layout.activity_team_mgmt); 
        handleTeamSelection();
        handlePlayerSelection((Spinner) findViewById(R.id.select_player_spinner));
        handleSave();
    }
    
    
    public void handleTeamSelection(){

    	teamSpinner = (Spinner) findViewById(R.id.select_team_spinner);
    	List<Team> list = this.teamService.getAll();
       	ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this, android.R.layout.simple_spinner_dropdown_item, list);
       	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	teamSpinner.setAdapter(adapter);
    	teamSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view, int position, long rowId) {
				EditText editText = (EditText) findViewById(R.id.editTeamName);
				Team team = (Team) teamSpinner.getItemAtPosition(position);	
				editText.setText(team.getName());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
    	});
    }
    
    public void handlePlayerSelection(Spinner spinner){

    	List<Player> list = this.playerService.getAll();
       	ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_spinner_dropdown_item, list);
       	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       	spinner.setAdapter(adapter);
       	spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view, int position, long rowId) {
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
    	});
    }
    
    
    protected void handleSave(){
    	this.findViewById(R.id.save).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				Team team = (Team) teamSpinner.getSelectedItem();
				EditText editText = (EditText) findViewById(R.id.editTeamName);
				team.setName(editText.getText().toString());
				teamService.save(team);
			}    		
    	});
    }

}
 