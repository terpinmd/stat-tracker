package com.hammond.stattracker.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.ui.admin.TeamManagementActivity;


public class SplashScreenActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    
    public void startGame(View view){
    	
    	// custom dialog
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_start_game);
		dialog.setTitle("Start a game");
    	
		TextView text = (TextView) dialog.findViewById(R.id.startGameSelectTeamLabel);
		text.setText("Android custom dialog example!");
	
		dialog.show();
		
	
    }
    
    
    public void openTeamManagement(View view){
    	startActivity(new Intent(SplashScreenActivity.this, TeamManagementActivity.class));
    }


}
