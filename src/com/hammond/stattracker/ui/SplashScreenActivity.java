package com.hammond.stattracker.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.ui.admin.TeamManagementActivity;
import com.hammond.stattracker.ui.components.TeamSpinnerAdapter;


public class SplashScreenActivity extends Activity {

	private final Activity context = this;

	private Spinner teamSpinner;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    
    public void startGame(View view){
    	
    	// custom dialog
		final Dialog dialog = new Dialog(this);
		final Game game = new Game();
		dialog.setContentView(R.layout.dialog_start_game);
		dialog.setTitle("Start a game");
    	
		buildTeamSpinner(dialog);
		buildOtherTeamSpinner(dialog);
	
		((Button) dialog.findViewById(R.id.start)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, PlayGameActivity.class); 				
				intent.putExtra("team", (Team) teamSpinner.getSelectedItem());
				Team myTeam = (Team) (((Spinner) dialog.findViewById(R.id.select_my_team_spinner)).getSelectedItem());
				game.setMyTeamId(myTeam.getId());
								
				Team vsTeam = (Team) (((Spinner) dialog.findViewById(R.id.select_vs_team_spinner)).getSelectedItem());
				game.setVsTeamId(vsTeam.getId());
				
				intent.putExtra("game", game);
				
				startActivity(intent);
			}			
		});
		dialog.show();
    }
    
    
    
    
    public void openTeamManagement(View view){
    	startActivity(new Intent(SplashScreenActivity.this, TeamManagementActivity.class));
    }

	public void buildTeamSpinner(Dialog dialog){
    	teamSpinner = (Spinner) dialog.findViewById(R.id.select_my_team_spinner);
    	teamSpinner.setAdapter(new TeamSpinnerAdapter(context));
    }
	
	public void buildOtherTeamSpinner(Dialog dialog){
    	Spinner spinner = (Spinner) dialog.findViewById(R.id.select_vs_team_spinner);
       	spinner.setAdapter(new TeamSpinnerAdapter(context));
    }
}
