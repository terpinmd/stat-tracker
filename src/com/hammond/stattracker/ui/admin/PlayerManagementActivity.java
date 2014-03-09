package com.hammond.stattracker.ui.admin;

import android.app.Activity;
import android.os.Bundle;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.service.PlayerService;
import com.hammond.stattracker.service.TeamService;


public class PlayerManagementActivity extends Activity {

    protected TeamService teamService;
	
    protected PlayerService playerService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamService = new TeamService(this);
        playerService = new PlayerService(this);
        setContentView(R.layout.activity_player_mgmt); 
        

        Player player = (Player) this.getIntent().getSerializableExtra("player");
        
        System.out.println("player " + player.getJersyNumber());    
    }  
    
   

}
 