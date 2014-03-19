package com.hammond.stattracker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;
import com.hammond.stattracker.ui.components.PlayerStatisticsAdapter;

public class PlayGameActivity extends Activity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);	
		
		final Team team = (Team) this.getIntent().getSerializableExtra("team");
		final Game game = (Game) this.getIntent().getSerializableExtra("game");			
		
		final ListView listView = (ListView) findViewById(R.id.gameRosterList);
		final ArrayAdapter<Player> adapter = new PlayerStatisticsAdapter(this, game, team);
		listView.setAdapter(adapter);
		listView.setTextFilterEnabled(true);
		
		
		//TODO for now get rid of the dialog and have the events inline.  Not sure we gain anything from the drop down
		//to select the statistic for now (other than being able to quit and not record it.  maybe have an undo button?)
			
		

		/*listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.dialog_player_statistic);
				dialog.setTitle("Track");
				dialog.show();
				
				final Player player = (Player) listView.getItemAtPosition(position);

				((Button) dialog.findViewById(R.id.groundBall)).setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View view) {
						statisticService.save(new LacrosseStatistic("GROUND_BALL"), game, player);
						statisticService.getGameStatisticsByPlayer(player, game);
					}			
				});
				
				((Button) dialog.findViewById(R.id.assist)).setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View view) {
						statisticService.save(new LacrosseStatistic("ASSIST"), game, player);
					}			
				});
				
				((Button) dialog.findViewById(R.id.goal)).setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View view) {
						statisticService.save(new LacrosseStatistic("GOAL"), game, player);
					}			
				});
			}
		});*/

	}

}
