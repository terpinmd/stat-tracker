package com.hammond.stattracker.dao;

import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_GAME_STATISTICS_GAME_ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_GAME_STATISTICS_PLAYER_ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_STATISTIC_NAME;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_STATISTIC_SPORT;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.TABLE_NAME_GAME_STATISTICS;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.TABLE_NAME_STATISTIC;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.hammond.stattracker.domain.AbstractStatistic;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.LacrosseStatistic;
import com.hammond.stattracker.domain.Player;

public class StatisticDao extends AbstractBaseDao<AbstractStatistic> {

	public StatisticDao(Context context) {
		super(context);
	}

	
	public void dbCreate(){
		this.onCreate(this.getWritableDatabase());
	}
	
	
	public List<AbstractStatistic> getStatisticsBySport(String sport){
		List<AbstractStatistic> items = new ArrayList<AbstractStatistic>();
		SQLiteDatabase db = this.getReadableDatabase();
	
		Cursor cursor = db.query(TABLE_NAME_STATISTIC, 
								 null, 
								 COLUMN_NAME_STATISTIC_SPORT + " = ?", 
								 new String[]{sport}, 
								 null, 
								 null, 
								 ID);
		
		if (cursor.moveToFirst()) {
			do {
				items.add(build(cursor));
			} while (cursor.moveToNext());
		}
		
		return items;
	}
	
	public AbstractStatistic getStatisticBySportAndName(String sport, String name){

		SQLiteDatabase db = this.getReadableDatabase();
	
		Cursor cursor = db.query(TABLE_NAME_STATISTIC, 
								 null, 
								 COLUMN_NAME_STATISTIC_SPORT + " = ? and " + 
										 COLUMN_NAME_STATISTIC_NAME + " = ?" , 
								 new String[]{sport, name}, 
								 null, 
								 null, 
								 ID);
		
		cursor.moveToFirst();
		return build(cursor);
	}
	

	public void save(AbstractStatistic statistic, Game game, Player player){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID, 1);
		values.put(COLUMN_NAME_GAME_STATISTICS_GAME_ID, game.getId());
		values.put(COLUMN_NAME_GAME_STATISTICS_PLAYER_ID, player.getId());
		db.insert(TABLE_NAME_GAME_STATISTICS, null, values);
	}
	
	
	//This should be a group by query so the results are like:
	// Player, Sport, Statistic, count
	public List<AbstractStatistic> getGameStatisticsByPlayer(Player player){
		List<AbstractStatistic> items = new ArrayList<AbstractStatistic>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		
		Cursor cursor = db.query(TABLE_NAME_GAME_STATISTICS, 
								 new String[] {COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID, " count(" + COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID +") "}, 
								 COLUMN_NAME_GAME_STATISTICS_PLAYER_ID + " = ?", 
								 new String[]{player.getId().toString()}, 
								 COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID , 
								 null, 
								 ID);
		
		//Cursor cursor = db.rawQuery("SELECT count(*), statistic_id FROM  game_statistics WHERE player_id = ? and game_id = ? group by statistic_id", 
		//							new String[]{player.getId().toString(), "1"});
		
		if (cursor.moveToFirst()) {
			do {
				DatabaseUtils.dumpCurrentRow(cursor);
			} while (cursor.moveToNext());
		}
		
		return items;
	}
	
	
	@Override
	protected AbstractStatistic build(Cursor cursor){
		AbstractStatistic item = new LacrosseStatistic(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_STATISTIC_NAME)));
		return item;
	}
	
}
