package com.hammond.stattracker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import static com.hammond.stattracker.dao.db.GameSchema.*;
import com.hammond.stattracker.domain.Game;

public class GameDao extends AbstractBaseDao<Game> {

	public GameDao(Context context) {
		super(context);
	}

	
	
	public void create(Game game){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(COLUMN_GAME_TITLE, game.getTitle());
		values.put( COLUMN_TEAM, game.getGameId());
		values.put(COLUMN_GAME_DATE, game.getDateTime());
		values.put(COLUMN_GAME_NOTES, game.getNotes());	
		long id = db.insert(TABLE_NAME_GAME, null, values);	
		game.setId(id);
	}



	@Override
	protected Game build(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}
}
