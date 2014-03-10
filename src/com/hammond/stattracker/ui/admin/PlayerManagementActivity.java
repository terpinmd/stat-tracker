package com.hammond.stattracker.ui.admin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.service.PlayerService;


public class PlayerManagementActivity extends Activity {


    private PlayerService playerService;
    
    private Player player;
    
	private EditText firstName , lastName , jersey;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerService = new PlayerService(this);
        setContentView(R.layout.activity_player_mgmt);        
        this.player = (Player) this.getIntent().getSerializableExtra("player");   
        this.player.setTeam((Team) this.getIntent().getSerializableExtra("team"));
        
        setFormFields();
   }  
    
   
    //TODO see if there is a way to have this binding done for us
    private void setFormFields(){
		this.firstName = (EditText) findViewById(R.id.firstNameText);
		this.firstName.setText(this.player.getFirstName());
		this.lastName = (EditText) findViewById(R.id.lastNameText);	
		this.lastName.setText(this.player.getLastName());
		this.jersey = (EditText) findViewById(R.id.jerseyText);
		if(this.player.getJersyNumber() != null)
			this.jersey.setText(this.player.getJersyNumber().toString());
    }
    
    public void save(View view){		
		player.setFirstName(firstName.getText().toString());
		player.setLastName(lastName.getText().toString());
		
		if(jersey.getText() != null)
			player.setJersyNumber(Integer.valueOf(jersey.getText().toString()));
		
		playerService.save(player);
    }
    
    public void delete(View view){
    	this.playerService.delete(player);
    }
}
 