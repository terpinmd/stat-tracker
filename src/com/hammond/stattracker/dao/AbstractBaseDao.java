package com.hammond.stattracker.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hammond.stattracker.dao.db.SchemaDefinition;

public class AbstractBaseDao extends SQLiteOpenHelper{

	public AbstractBaseDao(Context context) {
		super(context, SchemaDefinition.DATABASE_NAME, null, SchemaDefinition.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SchemaDefinition.getCreateTeamTableSql());
		db.execSQL(SchemaDefinition.getCreatePlayerTableSql());
		db.execSQL(SchemaDefinition.getCreateGameTableSql());
		db.execSQL(SchemaDefinition.getCreateGameStatisticsTableSql());
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
}
