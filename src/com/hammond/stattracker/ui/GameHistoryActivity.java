package com.hammond.stattracker.ui;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hammond.stattracker.R;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.service.GameService;

public class GameHistoryActivity extends Activity {

	private GameService gameService;

	private Activity context;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.gameService = new GameService(this);
		setContentView(R.layout.activity_game_history);
		context = this;

		final ListView listView = (ListView) findViewById(R.id.gameHistoryList);
		List<Game> games =  this.gameService.getAll();
		listView.setAdapter(new ArrayAdapter<Game>(this, android.R.layout.simple_list_item_1, games));
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,	long arg3) {
				Intent intent = new Intent(context, PlayGameActivity.class);
				Game game = (Game) listView.getItemAtPosition(position);
				intent.putExtra("game", game);
				intent.putExtra("isReadyOnly", true);
				startActivity(intent);
			}
			
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int position, long id) {
            	Game game = (Game) listView.getItemAtPosition(position);
                gameService.delete(game);
                return true;
            }
        }); 
	}
}
