package com.hammond.stattracker.dao;

import static com.hammond.stattracker.dao.db.PlayerDefinition.COLUMN_FIRST_NAME;
import static com.hammond.stattracker.dao.db.PlayerDefinition.COLUMN_JERSEY_NUMBER;
import static com.hammond.stattracker.dao.db.PlayerDefinition.COLUMN_LAST_NAME;
import static com.hammond.stattracker.dao.db.PlayerDefinition.COLUMN_TEAM;
import static com.hammond.stattracker.dao.db.PlayerDefinition.ID;
import static com.hammond.stattracker.dao.db.PlayerDefinition.TABLE_NAME_PLAYER;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;

public class PlayerDao extends AbstractBaseDao<Player>{

	private Context context;
	
	public PlayerDao(Context context) {
		super(context);
		this.context = context;
	}

	
	public void create(Player player){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(COLUMN_FIRST_NAME, player.getFirstName());
		values.put(COLUMN_LAST_NAME, player.getLastName());
		values.put(COLUMN_TEAM, player.getTeam().getId());
		values.put(COLUMN_JERSEY_NUMBER, player.getJersyNumber());	
		db.insert(TABLE_NAME_PLAYER, null, values);	
	}
	
	
	public void save(Player player){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(COLUMN_FIRST_NAME, player.getFirstName());
		values.put(COLUMN_LAST_NAME, player.getLastName());
		values.put(COLUMN_TEAM, player.getTeam().getId());
		values.put(COLUMN_JERSEY_NUMBER, player.getJersyNumber());	
		db.update(TABLE_NAME_PLAYER, values, "id = ?", new String[] {player.getId().toString()});
	}
	
	
	public void delete(Player player){
		SQLiteDatabase db = this.getWritableDatabase();		
		new StatisticDao(context).deleteByPlayer(player);
		db.delete(TABLE_NAME_PLAYER, "id = ?", new String[] {player.getId().toString()});
	}
	
	public void deleteByTeam(Team team){
		SQLiteDatabase db = this.getWritableDatabase();		
		db.delete(TABLE_NAME_PLAYER, "team_id = ?", new String[] {team.getId().toString()});
	}	
	
	public List<Player> getPlayersForTeam(Team team){
		List<Player> players = new ArrayList<Player>();
		SQLiteDatabase db = this.getReadableDatabase();
	
		Cursor cursor = db.query(TABLE_NAME_PLAYER, 
								 null, 
								 COLUMN_TEAM + " = ?", 
								 new String[]{team.getId().toString()}, 
								 null, 
								 null, 
								 COLUMN_JERSEY_NUMBER);
		
		if (cursor.moveToFirst()) {
			do {
				players.add(build(cursor));
			} while (cursor.moveToNext());
		}
		
		return players;
	}
	
	
	public List<Player> getAll(){
		List<Player> players = new ArrayList<Player>();
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from player", null);
	
		if (cursor.moveToFirst()) {
			do {
				players.add(build(cursor));
			} while (cursor.moveToNext());
		}
		return players;
	}

	@Override
	protected Player build(Cursor cursor){
		Player player = new Player();
		player.setId(cursor.getLong(cursor.getColumnIndex(ID)));
		
		//player.setTeam(teamDaoImpl.getTeam(cursor.getLong(cursor.getColumnIndex(COLUMN_TEAM))));
		
		player.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
		player.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));
		player.setJersyNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_JERSEY_NUMBER)));
		return player;
	}

}
