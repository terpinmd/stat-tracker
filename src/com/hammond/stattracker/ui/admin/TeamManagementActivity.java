package com.hammond.stattracker.ui.admin;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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


public class TeamManagementActivity extends Activity implements OnItemSelectedListener{

    protected TeamService teamService;
	
    protected PlayerService playerService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamService = new TeamService(this);
        playerService = new PlayerService(this);
        setContentView(R.layout.activity_team_mgmt);       
        buildTeamsSpinner();
        buildPlayersSpinner();
    }

	public void buildTeamsSpinner(){
    	Spinner spinner = (Spinner) findViewById(R.id.select_team_spinner);
    	List<Team> list = this.teamService.getAll();
       	ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this, android.R.layout.simple_spinner_dropdown_item, list);
       	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       	spinner.setAdapter(adapter);
       	spinner.setOnItemSelectedListener(this);
    }
    
    public void buildPlayersSpinner(){
    	final Spinner spinner = (Spinner) findViewById(R.id.select_player_spinner);
    	List<Player> list = this.playerService.getAll();
       	ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_spinner_dropdown_item, list);
       	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       	spinner.setAdapter(adapter);
    }
    
    
      
    public void save(View view){
		Team team = getSelectedTeam();
		EditText editText = (EditText) findViewById(R.id.editTeamName);
		team.setName(editText.getText().toString());
		teamService.save(team);    	
    }
    
    
    public void editRoster(View view){
       	final Intent intent = new Intent(this, PlayerManagementActivity.class);
       	intent.putExtra("team", this.getSelectedTeam());
       	startActivity(intent);	    	
    }
    
    
    private Team getSelectedTeam(){
    	Spinner spinner =  (Spinner) findViewById(R.id.select_team_spinner);
    	return (Team) spinner.getSelectedItem();
    }
    

	@Override
	public void onItemSelected(AdapterView<?> adapter, View view, int position, long rowId) {		
		EditText editText = (EditText) findViewById(R.id.editTeamName);
		Team team = getSelectedTeam();
		editText.setText(team.getName());
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {}


}
 