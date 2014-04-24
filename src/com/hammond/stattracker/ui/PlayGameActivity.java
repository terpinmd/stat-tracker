package com.hammond.stattracker.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.service.GameService;
import com.hammond.stattracker.ui.components.PlayerStatisticsAdapter;

public class PlayGameActivity extends Activity {

	private GameService gameService;

	private Game game;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.gameService = new GameService(this);
		setContentView(R.layout.activity_play_game);

		this.game = (Game) this.getIntent().getSerializableExtra("game");

		ListView listView = (ListView) findViewById(R.id.gameRosterList);
		ArrayAdapter<Player> adapter = new PlayerStatisticsAdapter(this, game, this.isReadyOnly());
		listView.setAdapter(adapter);
	}

	public void close(View view) {
		
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.dialog_finish_game, null);

		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setView(promptView);

		final EditText input = (EditText) promptView.findViewById(R.id.vsTeamScoreInput);
		final Activity context = this;
		dialog.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                game.setVsTeamScore(Long.valueOf(input.getText().toString()));
                gameService.save(game);
                context.finish();
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

		AlertDialog alertD = dialog.create();
		alertD.show();
		
	}
	
	public boolean isReadyOnly(){
		Boolean value = (Boolean) this.getIntent().getSerializableExtra("isReadyOnly");
		if(value == null)
			return false;
		return value.booleanValue();
	}

}
