package com.hammond.stattracker.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hammond.stattracker.dao.db.TeamSchema;
import com.hammond.stattracker.domain.Team;

public class TeamDaoImpl extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Statistics.db";

	
	private static final String SQL_CREATE_ENTRIES =
		    "CREATE TABLE team (" +
		    "id INTEGER PRIMARY KEY," +
		    "team_name TEXT )";
	
	public TeamDaoImpl(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		  System.out.println(SQL_CREATE_ENTRIES);
		  db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}
	
	public void create(){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(TeamSchema.COLUMN_TEAM_NAME, "MARYLAND");
		db.insert(TeamSchema.TABLE_NAME, null, values);	
	}
	
	public List<Team> getAll(){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from team", null);
		List<Team> teams = new ArrayList<Team>();
		
		if (cursor.moveToFirst()) {
			do {
				Team team = new Team();
				team.setName(cursor.getString(cursor.getColumnIndex(TeamSchema.COLUMN_TEAM_NAME)));
				team.setId(cursor.getInt(cursor.getColumnIndex("id")));
				teams.add(team);
			} while (cursor.moveToNext());
		}
		return teams;
	}

}
