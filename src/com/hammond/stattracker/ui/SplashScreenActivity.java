package com.hammond.stattracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hammond.stattracker.R;
import com.hammond.stattracker.ui.admin.TeamManagementActivity;


public class SplashScreenActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handleTeamMgmtOption();
        handleStatisticsOption();
    }

    
    protected void handleTeamMgmtOption(){
        findViewById(R.id.team_management).setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				System.out.println("Creating a team son!");
				startActivity(new Intent(SplashScreenActivity.this, TeamManagementActivity.class));
			}
		});
    }
    
    protected void handleStatisticsOption(){
        findViewById(R.id.stat_management).setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				System.out.println("Creating a statistic son!");
			}
		});
    }

}
