package com.hammond.stattracker.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hammond.stattracker.dao.db.SchemaDefinition;
import com.hammond.stattracker.domain.Team;

public class TeamDao extends AbstractBaseDao {

	public TeamDao(Context context) {
		super(context);
	}

	
	public void dbCreate(){
		this.onCreate(this.getWritableDatabase());
	}
	
	
	public Team create(Team team){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(SchemaDefinition.COLUMN_TEAM_NAME, team.getName());
		long id = db.insert(SchemaDefinition.TABLE_NAME_TEAM, null, values);	
		team.setId(id);
		return team;
	}
	
	
	public void save(Team team){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(SchemaDefinition.COLUMN_TEAM_NAME, team.getName());
		db.update(SchemaDefinition.TABLE_NAME_TEAM, values, "id = ?", new String[] {team.getId().toString()});
	}
	
	
	public List<Team> getAll(){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from team", null);
		List<Team> teams = new ArrayList<Team>();
		
		if (cursor.moveToFirst()) {
			do {
				teams.add(getTeam(cursor));
			} while (cursor.moveToNext());
		}
		return teams;
	}

	
	public Team getTeam(Long id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from team where id = ?", new String[] {id.toString()});
		return this.getTeam(cursor);
	}
	
	private Team getTeam(Cursor cursor){
		Team team = new Team();
		team.setName(cursor.getString(cursor.getColumnIndex(SchemaDefinition.COLUMN_TEAM_NAME)));
		team.setId(cursor.getLong(cursor.getColumnIndex(SchemaDefinition.ID)));
		return team;
	}
	
}
