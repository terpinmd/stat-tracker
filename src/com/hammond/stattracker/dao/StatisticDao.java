package com.hammond.stattracker.dao;

import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_GAME_STATISTICS_GAME_ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_GAME_STATISTICS_PLAYER_ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.COLUMN_NAME_STATISTIC_NAME;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.ID;
import static com.hammond.stattracker.dao.db.StatisticsDefinition.TABLE_NAME_GAME_STATISTICS;

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
import com.hammond.stattracker.domain.Team;

public class StatisticDao extends AbstractBaseDao<AbstractStatistic> {

	public StatisticDao(Context context) {
		super(context);
	}

	
	public void dbCreate(){
		this.onCreate(this.getWritableDatabase());
	}
	
	
	public void save(AbstractStatistic statistic, Game game, Player player){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID, statistic.getId());
		values.put(COLUMN_NAME_GAME_STATISTICS_GAME_ID, game.getId());
		values.put(COLUMN_NAME_GAME_STATISTICS_PLAYER_ID, player.getId());
		db.insert(TABLE_NAME_GAME_STATISTICS, null, values);		
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
		
		//Cursor cursor = db.rawQuery("SELECT count(*), statistic_id FROM  game_statistics WHERE player_id = ? and game_id = ? group by statistic_id", 
		//							new String[]{player.getId().toString(), "1"});
		int count = 0;
		if (cursor.moveToFirst()) {		
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			DatabaseUtils.dumpCurrentRow(cursor);
			count = cursor.getInt(1);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + count);			
			
		}
		
		return count;
	}
	
	
	
	public List<Object> getPlayerStatisticsForGame(Team team, Game game){
		
		
		return null;
	}
	
	
	@Override
	protected AbstractStatistic build(Cursor cursor){
		AbstractStatistic item = new LacrosseStatistic(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_STATISTIC_NAME)));
		return item;
	}
	
}
