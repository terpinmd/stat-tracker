package com.hammond.stattracker.dao;

import static com.hammond.stattracker.dao.db.GameSchema.COLUMN_GAME_DATE;
import static com.hammond.stattracker.dao.db.GameSchema.COLUMN_GAME_NOTES;
import static com.hammond.stattracker.dao.db.GameSchema.COLUMN_GAME_TITLE;
import static com.hammond.stattracker.dao.db.GameSchema.COLUMN_MY_TEAM;
import static com.hammond.stattracker.dao.db.GameSchema.COLUMN_VS_TEAM;
import static com.hammond.stattracker.dao.db.GameSchema.TABLE_NAME_GAME;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.Team;
public class GameDao extends AbstractBaseDao<Game> {

	private TeamDao teamDao;
	
	public GameDao(Context context) {
		super(context);
		teamDao = new TeamDao(context);
	}

	
	
	public void create(Game game){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(COLUMN_GAME_TITLE, game.getTitle());
		values.put(COLUMN_GAME_DATE, game.getDateTime());
		values.put(COLUMN_MY_TEAM, game.getMyTeam().getId());
		values.put(COLUMN_VS_TEAM, game.getVsTeam().getId());
		values.put(COLUMN_GAME_NOTES, game.getNotes());	
		long id = db.insert(TABLE_NAME_GAME, null, values);	
		game.setId(id);
	}

	
	public void save(Game game){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(COLUMN_GAME_TITLE, game.getTitle());
		values.put(COLUMN_GAME_DATE, game.getDateTime());
		values.put(COLUMN_MY_TEAM, game.getMyTeam().getId());
		values.put(COLUMN_VS_TEAM, game.getVsTeam().getId());
		values.put(COLUMN_GAME_NOTES, game.getNotes());	
		db.update(TABLE_NAME_GAME, values, "id = ?", new String[]{ game.getId().toString() } );	
	}

	public List<Game> getAll(Team team){
		SQLiteDatabase db = this.getReadableDatabase();
		
		List<Game> games = new ArrayList<Game>();
		
		Cursor cursor = db.query(TABLE_NAME_GAME, null, COLUMN_MY_TEAM + "=?", new String[]{team.getId().toString()}, null, null, COLUMN_GAME_DATE + " desc");
		
		if (cursor.moveToFirst()) {
			do {
				games.add(build(cursor));
			} while (cursor.moveToNext());
		}
		
		return games;
	}


	@Override
	protected Game build(Cursor c) {
		Game game = new Game();
		game.setId(c.getLong(c.getColumnIndex("id")));
		game.setTitle(c.getString(c.getColumnIndex(COLUMN_GAME_TITLE)));
		game.setDateTime(c.getLong(c.getColumnIndex(COLUMN_GAME_DATE)));
		game.setNotes(c.getString(c.getColumnIndex(COLUMN_GAME_NOTES)));
		game.setMyTeam(this.teamDao.getTeam(c.getLong(c.getColumnIndex(COLUMN_MY_TEAM))));
		return game;
	}
}
