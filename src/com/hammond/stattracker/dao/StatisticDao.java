package com.hammond.stattracker.dao;

import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_GAME_STATISTICS_GAME_ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_GAME_STATISTICS_PLAYER_ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_STATISTIC_NAME;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.TABLE_NAME_GAME_STATISTICS;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hammond.stattracker.domain.AbstractStatistic;
import com.hammond.stattracker.domain.Game;
import com.hammond.stattracker.domain.LacrosseStatistic;
import com.hammond.stattracker.domain.Player;

public class StatisticDao extends AbstractBaseDao<AbstractStatistic> {

	public StatisticDao(Context context) {
		super(context);
	}

	

	public void save(AbstractStatistic statistic, Game game, Player player){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID, statistic.getId());
		values.put(COLUMN_NAME_GAME_STATISTICS_GAME_ID, game.getId());
		values.put(COLUMN_NAME_GAME_STATISTICS_PLAYER_ID, player.getId());
		db.insert(TABLE_NAME_GAME_STATISTICS, null, values);
		db.close();
	}
	
	
	//This should be a group by query so the results are like:
	// Player, Sport, Statistic, count
	public int getStatisticCount(AbstractStatistic statistic, Player player, Game game){

		SQLiteDatabase db = this.getReadableDatabase();
		
		
		Cursor cursor = db.query(TABLE_NAME_GAME_STATISTICS, 
								 new String[] {COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID, " count(" + COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID +") "}, 
								 COLUMN_NAME_GAME_STATISTICS_PLAYER_ID 		+ " = ? and " + 
								 COLUMN_NAME_GAME_STATISTICS_GAME_ID   		+ " = ? and " + 
								 COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID 	+ " = ?",
								 new String[]{player.getId().toString(), game.getId().toString(), statistic.getId().toString()}, 
								 COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID , 
								 null, 
								 ID);
		
		int count = 0;
		if (cursor.moveToFirst()) {		
			//DatabaseUtils.dumpCurrentRow(cursor);
			count = cursor.getInt(1);
		}
		
		db.close();
		
		return count;
	}
	
	
	public void deleteByPlayer(Player player){
		SQLiteDatabase db = this.getReadableDatabase();
		db.delete(TABLE_NAME_GAME_STATISTICS, " player_id = ? ", new String[]{player.getId().toString()});
	}
	
	public void deleteLast(AbstractStatistic statistic, Player player, Game game){

		SQLiteDatabase db = this.getReadableDatabase();
				
		Cursor cursor = db.query(TABLE_NAME_GAME_STATISTICS, 
								 new String[] {COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID, " max(id) "}, 
								 COLUMN_NAME_GAME_STATISTICS_PLAYER_ID 		+ " = ? and " + 
								 COLUMN_NAME_GAME_STATISTICS_GAME_ID   		+ " = ? and " + 
								 COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID 	+ " = ?",
								 new String[]{player.getId().toString(), game.getId().toString(), statistic.getId().toString()}, 
								 COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID , 
								 null, 
								 ID);
		
		if (cursor.moveToFirst()) {		
			Integer max = cursor.getInt(1);
			this.getWritableDatabase().delete(TABLE_NAME_GAME_STATISTICS, "id = ?", new String[]{max.toString()});
			this.getWritableDatabase().close();
		}
		db.close();
	}
	

	@Override
	protected AbstractStatistic build(Cursor cursor){
		AbstractStatistic item = new LacrosseStatistic(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_STATISTIC_NAME)));
		return item;
	}
	
}
