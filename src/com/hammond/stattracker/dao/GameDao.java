package com.hammond.stattracker.dao;

import static com.hammond.stattracker.dao.db.GameSchema.*;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.Team;
public class GameDao extends AbstractBaseDao<Game> {

	private Context context;

	public GameDao(Context context) {
		super(context);
		this.context = context;
	}

	
	
	public void create(Game game){
		SQLiteDatabase db = this.getWritableDatabase();	
		//db.execSQL("alter table game add column vs_team_score INTEGER");
		ContentValues values = new ContentValues();		
		values.put(COLUMN_GAME_TITLE, game.getTitle());
		values.put(COLUMN_GAME_DATE, game.getDateTime());
		values.put(COLUMN_MY_TEAM, game.getMyTeam().getId());
		values.put(COLUMN_VS_TEAM, game.getVsTeam().getId());
		values.put(COLUMN_GAME_NOTES, game.getNotes());
		values.put(COLUMN_VS_TEAM_SCORE, game.getVsTeamScore());
		
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
		values.put(COLUMN_VS_TEAM_SCORE, game.getVsTeamScore());
		db.update(TABLE_NAME_GAME, values, "id = ?", new String[]{ game.getId().toString() } );	
	}

	public void deleteByTeam(Team team){
		SQLiteDatabase db = this.getWritableDatabase();		
		db.delete(TABLE_NAME_GAME, "my_team_id = ?", new String[]{ team.getId().toString() } );
		db.delete(TABLE_NAME_GAME, "vs_team_id = ?", new String[]{ team.getId().toString() } );	
	}
	
	public void deleteByGame(Game game){
		SQLiteDatabase db = this.getWritableDatabase();		
		db.delete(TABLE_NAME_GAME, "id = ?", new String[]{ game.getId().toString() } );
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

	
	public List<Game> getAll(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		List<Game> games = new ArrayList<Game>();
		
		Cursor cursor = db.query(TABLE_NAME_GAME, null, null, null, null, null, COLUMN_GAME_DATE + " desc");
		
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
		
		TeamDao teamDao = new TeamDao(context);
		
		Team myTeam = teamDao.getTeam(c.getLong(c.getColumnIndex(COLUMN_MY_TEAM)));
		game.setMyTeam(myTeam);
		
		Team vsTeam = teamDao.getTeam(c.getLong(c.getColumnIndex(COLUMN_VS_TEAM)));
		game.setVsTeam(vsTeam);
		
		game.setVsTeamScore(c.getLong(c.getColumnIndex(COLUMN_VS_TEAM_SCORE)));
		
		return game;
	}
}
