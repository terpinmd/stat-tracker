package com.hammond.stattracker.dao;

import static com.hammond.stattracker.dao.db.TeamDefinition.COLUMN_TEAM_NAME;
import static com.hammond.stattracker.dao.db.TeamDefinition.ID;
import static com.hammond.stattracker.dao.db.TeamDefinition.TABLE_NAME_TEAM;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hammond.stattracker.domain.Team;

public class TeamDao extends AbstractBaseDao<Team> {

	private Context context;
		
	public TeamDao(Context context) {
		super(context);
		this.context = context;
	}
	
	public void dbCreate(){
		this.onCreate(this.getWritableDatabase());
	}
	
	
	public Team create(Team team){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(COLUMN_TEAM_NAME, team.getName());
		long id = db.insert(TABLE_NAME_TEAM, null, values);	
		team.setId(id);
		return team;
	}
	
	
	public void save(Team team){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(COLUMN_TEAM_NAME, team.getName());
		db.update(TABLE_NAME_TEAM, values, "id = ?", new String[] {team.getId().toString()});
	}
	
	public void delete(Team team){
		SQLiteDatabase db = this.getReadableDatabase();
		new PlayerDao(context).deleteByTeam(team);
		new GameDao(context).deleteByTeam(team);
		db.delete(TABLE_NAME_TEAM, "id = ?", new String[] {team.getId().toString()});		
	}
	
	public List<Team> getAll(){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from team", null);
		List<Team> teams = new ArrayList<Team>();
		
		if (cursor.moveToFirst()) {
			do {
				teams.add(build(cursor));
			} while (cursor.moveToNext());
		}
		return teams;
	}

	
	public Team getTeam(Long id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from team where id = ?", new String[] {id.toString()});
		if(!cursor.moveToFirst()){
			return null;
		}
		return this.build(cursor);
	}
	
	@Override
	protected Team build(Cursor cursor){
		Team team = new Team();
		team.setName(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_NAME)));
		team.setId(cursor.getLong(cursor.getColumnIndex(ID)));
		return team;
	}
	
}
