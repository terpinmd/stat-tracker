package com.hammond.stattracker.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hammond.stattracker.dao.db.SchemaDefinition;
import com.hammond.stattracker.domain.Player;
import com.hammond.stattracker.domain.Team;

public class PlayerDao extends AbstractBaseDao{

	TeamDao teamDaoImpl;
	
	public PlayerDao(Context context) {
		super(context);
		teamDaoImpl = new TeamDao(context);
	}

	

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}
	
	public void create(Player player){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(SchemaDefinition.COLUMN_FIRST_NAME, player.getFirstName());
		values.put(SchemaDefinition.COLUMN_LAST_NAME, player.getFirstName());
		values.put(SchemaDefinition.COLUMN_TEAM, player.getTeam().getId());
		values.put(SchemaDefinition.COLUMN_JERSEY_NUMBER, player.getJersyNumber());	
		db.insert(SchemaDefinition.TABLE_NAME_PLAYER, null, values);	
	}
	
	public List<Player> getPlayersForTeam(Team team){
		List<Player> players = new ArrayList<Player>();
		SQLiteDatabase db = this.getReadableDatabase();
	
		Cursor cursor = db.query(SchemaDefinition.TABLE_NAME_PLAYER, 
								 null, 
								 SchemaDefinition.COLUMN_TEAM + " = ?", 
								 new String[]{team.getId().toString()}, 
								 null, 
								 null, 
								 SchemaDefinition.COLUMN_JERSEY_NUMBER);
		
		if (cursor.moveToFirst()) {
			do {
				players.add(getPlayer(cursor));
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
				players.add(getPlayer(cursor));
			} while (cursor.moveToNext());
		}
		return players;
	}

	
	private Player getPlayer(Cursor cursor){
		Player player = new Player();
		player.setId(cursor.getLong(cursor.getColumnIndex(SchemaDefinition.ID)));
		
		//player.setTeam(teamDaoImpl.getTeam(cursor.getLong(cursor.getColumnIndex(SchemaDefinition.COLUMN_TEAM))));
		
		player.setFirstName(cursor.getString(cursor.getColumnIndex(SchemaDefinition.COLUMN_FIRST_NAME)));
		player.setLastName(cursor.getString(cursor.getColumnIndex(SchemaDefinition.COLUMN_LAST_NAME)));
		player.setJersyNumber(cursor.getInt(cursor.getColumnIndex(SchemaDefinition.COLUMN_JERSEY_NUMBER)));
		return player;
	}

}
