package com.hammond.stattracker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hammond.stattracker.dao.db.GameSchema;
import com.hammond.stattracker.dao.db.SchemaDefinition;

public abstract class AbstractBaseDao<T> extends SQLiteOpenHelper{

	public AbstractBaseDao(Context context) {
		super(context, SchemaDefinition.DATABASE_NAME, null, SchemaDefinition.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SchemaDefinition.getCreateTeamTableSql());
		db.execSQL(SchemaDefinition.getCreatePlayerTableSql());
		db.execSQL(GameSchema.getCreateGameTableSql());
		db.execSQL(SchemaDefinition.getCreateGameStatisticsTableSql());
		db.execSQL(SchemaDefinition.getCreateStatisticsTableSql());
		createDefaultStatistics(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void createDefaultStatistics(SQLiteDatabase db){
		ContentValues values = new ContentValues();		
		values.put(SchemaDefinition.COLUMN_NAME_STATISTIC_NAME, "GROUND BALL");
		values.put(SchemaDefinition.COLUMN_NAME_STATISTIC_SPORT, "LACROSSE");
		db.insert(SchemaDefinition.TABLE_NAME_STATISTIC, null, values);
		
		values = new ContentValues();		
		values.put(SchemaDefinition.COLUMN_NAME_STATISTIC_NAME, "ASSIST");
		values.put(SchemaDefinition.COLUMN_NAME_STATISTIC_SPORT, "LACROSSE");
		db.insert(SchemaDefinition.TABLE_NAME_STATISTIC, null, values);
		
		values = new ContentValues();		
		values.put(SchemaDefinition.COLUMN_NAME_STATISTIC_NAME, "GOAL");
		values.put(SchemaDefinition.COLUMN_NAME_STATISTIC_SPORT, "LACROSSE");
		db.insert(SchemaDefinition.TABLE_NAME_STATISTIC, null, values);
	}
	
	protected abstract T build(Cursor c);
}
