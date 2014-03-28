package com.hammond.stattracker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hammond.stattracker.dao.db.GameSchema;
import com.hammond.stattracker.dao.db.PlayerDefinition;
import com.hammond.stattracker.dao.db.StatisticsDefinition;
import com.hammond.stattracker.dao.db.TeamDefinition;
public abstract class AbstractBaseDao<T> extends SQLiteOpenHelper{

	
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Statistics.db";

	
	public AbstractBaseDao(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("PRAGMA foreign_keys=ON;");
		db.execSQL(TeamDefinition.getCreateTeamTableSql());
		db.execSQL(PlayerDefinition.getCreatePlayerTableSql());
		db.execSQL(GameSchema.getCreateGameTableSql());
		db.execSQL(StatisticsDefinition.getCreateGameStatisticsTableSql());
		db.execSQL(StatisticsDefinition.getCreateStatisticsTableSql());		
		createDefaultStatistics(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void createDefaultStatistics(SQLiteDatabase db){
		ContentValues values = new ContentValues();		
		values.put(StatisticsDefinition.COLUMN_NAME_STATISTIC_NAME, "GROUND BALL");
		values.put(StatisticsDefinition.COLUMN_NAME_STATISTIC_SPORT, "LACROSSE");
		db.insert(StatisticsDefinition.TABLE_NAME_STATISTIC, null, values);
		
		values = new ContentValues();		
		values.put(StatisticsDefinition.COLUMN_NAME_STATISTIC_NAME, "ASSIST");
		values.put(StatisticsDefinition.COLUMN_NAME_STATISTIC_SPORT, "LACROSSE");
		db.insert(StatisticsDefinition.TABLE_NAME_STATISTIC, null, values);
		
		values = new ContentValues();		
		values.put(StatisticsDefinition.COLUMN_NAME_STATISTIC_NAME, "GOAL");
		values.put(StatisticsDefinition.COLUMN_NAME_STATISTIC_SPORT, "LACROSSE");
		db.insert(StatisticsDefinition.TABLE_NAME_STATISTIC, null, values);
	}
	
	protected abstract T build(Cursor c);
}
