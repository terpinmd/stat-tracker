package com.hammond.stattracker.ui.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;
import com.hammond.stattracker.service.TeamService;
import com.hammond.stattracker.ui.components.PlayerSpinnerAdapter;
import com.hammond.stattracker.ui.components.TeamSpinnerAdapter;


public class TeamManagementActivity extends Activity implements OnItemSelectedListener{

    protected TeamService teamService;
	
    protected PlayerService playerService;
    
    private Spinner teamSpinner;
    
    private Spinner playerSpinner;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamService = new TeamService(this);
        playerService = new PlayerService(this);
        //playerService.createDefaults();
        setContentView(R.layout.activity_team_mgmt);       
        teamSpinner = (Spinner) findViewById(R.id.select_team_spinner);
        playerSpinner = (Spinner) findViewById(R.id.select_player_spinner);
        refresh();
    }

	public void buildTeamsSpinner(){
		teamSpinner.setAdapter(new TeamSpinnerAdapter(this));
		teamSpinner.setOnItemSelectedListener(this);
    }
    
    public void buildPlayersSpinner(){
    	playerSpinner.setAdapter(new PlayerSpinnerAdapter(this, this.getSelectedTeam()));
    }

    public void deleteTeam(View view){
    	this.teamService.delete((Team) teamSpinner.getSelectedItem());
    	teamSpinner.setSelection(0);
    	refresh();
    }
    
    
    public void addTeam(View view){
    	this.toggleControlsActions(false);
    }
    
    public void toggleControlsActions(boolean value){
    	
    	teamSpinner.setEnabled(value);
    	teamSpinner.setVisibility(value == false ? View.INVISIBLE : View.VISIBLE);
    	
    	playerSpinner.setEnabled(value);
    	playerSpinner.setVisibility(value == false ? View.INVISIBLE : View.VISIBLE);
    	Button addPlayer = (Button) findViewById(R.id.addPlayer);
    	addPlayer.setEnabled(value);   
    	Button editPlayer = (Button) findViewById(R.id.editPlayer);
    	editPlayer.setEnabled(value && (playerSpinner.getAdapter().getCount() > 0));    

    	Button deleteTeam = (Button) findViewById(R.id.deleteTeam);
    	deleteTeam.setEnabled(value);  
    	
    	if(!value){
    		EditText editText = (EditText) findViewById(R.id.editTeamName);
    		editText.setText(null);
    	}    	
    	
    }
      
    
    public void save(View view){
		Team team = teamSpinner.isEnabled() && teamSpinner.getCount() > 0 ? getSelectedTeam() : new Team();
		EditText editText = (EditText) findViewById(R.id.editTeamName);
		team.setName(editText.getText().toString());
		teamService.save(team);    	
		refresh();
    }
    
    
    public void editRoster(View view){
       	final Intent intent = new Intent(this, PlayerManagementActivity.class);
    	Player player = this.getSelectedPlayer();
    	player.setTeam(this.getSelectedTeam());
       	intent.putExtra("player", player);
       	startActivity(intent);	    	
    }
    
    public void addPlayer(View view){
       	final Intent intent = new Intent(this, PlayerManagementActivity.class);
       	Player player = new Player();
       	player.setTeam(this.getSelectedTeam());
       	intent.putExtra("player", player);
       	startActivity(intent);	     	
    }
        
    private Team getSelectedTeam(){
    	return (Team) teamSpinner.getSelectedItem();
    }
 
    private Player getSelectedPlayer(){
    	return (Player) playerSpinner.getSelectedItem();
    }
    

	@Override
	public void onItemSelected(AdapterView<?> adapter, View view, int position, long rowId) {		
		EditText editText = (EditText) findViewById(R.id.editTeamName);
		Team team = getSelectedTeam();
		editText.setText(team.getName());		
		buildPlayersSpinner();
        toggleControlsActions(true);
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {}

	
	@Override
	public void onResume() { 
		super.onResume();
		refresh();
	}
	
	private void refresh(){
		buildPlayersSpinner();
		buildTeamsSpinner();
		toggleControlsActions(true);
	}

}
 