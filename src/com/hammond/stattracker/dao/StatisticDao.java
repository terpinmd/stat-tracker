package com.hammond.stattracker.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.hammond.stattracker.dao.db.SchemaDefinition;
import com.hammond.stattracker.domain.AbstractStatistic;
import com.hammond.stattracker.domain.LacrosseStatistic;
import com.hammond.stattracker.domain.Player;

public class StatisticDao extends AbstractBaseDao {

	public StatisticDao(Context context) {
		super(context);
	}

	
	public void dbCreate(){
		this.onCreate(this.getWritableDatabase());
	}
	
	
	public List<AbstractStatistic> getStatisticsBySport(String sport){
		List<AbstractStatistic> items = new ArrayList<AbstractStatistic>();
		SQLiteDatabase db = this.getReadableDatabase();
	
		Cursor cursor = db.query(SchemaDefinition.TABLE_NAME_STATISTIC, 
								 null, 
								 SchemaDefinition.COLUMN_NAME_STATISTIC_SPORT + " = ?", 
								 new String[]{sport}, 
								 null, 
								 null, 
								 SchemaDefinition.ID);
		
		if (cursor.moveToFirst()) {
			do {
				items.add(get(cursor));
			} while (cursor.moveToNext());
		}
		
		return items;
	}
	
	public AbstractStatistic getStatisticBySportAndName(String sport, String name){

		SQLiteDatabase db = this.getReadableDatabase();
	
		Cursor cursor = db.query(SchemaDefinition.TABLE_NAME_STATISTIC, 
								 null, 
								 SchemaDefinition.COLUMN_NAME_STATISTIC_SPORT + " = ? and " + 
										 SchemaDefinition.COLUMN_NAME_STATISTIC_NAME + " = ?" , 
								 new String[]{sport, name}, 
								 null, 
								 null, 
								 SchemaDefinition.ID);
		
		cursor.moveToFirst();
		return get(cursor);
	}
	
	private AbstractStatistic get(Cursor cursor){
		AbstractStatistic item = new LacrosseStatistic();
		item.setName(cursor.getString(cursor.getColumnIndex(SchemaDefinition.COLUMN_NAME_STATISTIC_NAME)));
		return item;
	}
	
	
	public void save(AbstractStatistic statistic, int gameId, Player player){
		SQLiteDatabase db = this.getWritableDatabase();		
		ContentValues values = new ContentValues();		
		values.put(SchemaDefinition.COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID, 1);
		values.put(SchemaDefinition.COLUMN_NAME_GAME_STATISTICS_GAME_ID, gameId);
		values.put(SchemaDefinition.COLUMN_NAME_GAME_STATISTICS_PLAYER_ID, player.getId());
		db.insert(SchemaDefinition.TABLE_NAME_GAME_STATISTICS, null, values);
	}
	
	//select statistic_id as "statistic", count(statistic_id)
	//FROM marketplace.game_statistics
	//group by statistic_id


	
	//This should be a group by query so the results are like:
	// Player, Sport, Statistic, count
	public List<AbstractStatistic> getGameStatisticsByPlayer(Player player){
		List<AbstractStatistic> items = new ArrayList<AbstractStatistic>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		System.out.println("player" + player);
	
		Cursor tmp = db.rawQuery("select * from game_statistics", null);
		
		if(tmp.moveToFirst()){
			do{
				StringBuffer buffer = new StringBuffer();
				buffer.append("insert into game_statistics (game_id, player_id, statistic_id) values (");
				buffer.append(tmp.getInt(1)).append(", ");
				buffer.append(tmp.getInt(2)).append(", ");
				buffer.append(tmp.getInt(3));
				buffer.append(");");
				System.out.println(buffer.toString());
				DatabaseUtils.dumpCurrentRow(tmp);
			}while (tmp.moveToNext());
		}
		
		
//		Cursor cursor = db.query(SchemaDefinition.TABLE_NAME_GAME_STATISTICS, 
//								 new String[] {SchemaDefinition.COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID, " count(" + SchemaDefinition.COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID +") "}, 
//								 SchemaDefinition.COLUMN_NAME_GAME_STATISTICS_PLAYER_ID + " = ?", 
//								 new String[]{player.getId().toString()}, 
//								 SchemaDefinition.COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID , 
//								 null, 
//								 SchemaDefinition.ID);
		
		Cursor cursor = db.rawQuery("SELECT count(*), statistic_id FROM  game_statistics WHERE player_id = ? and game = ? group by statistic_id", 
									new String[]{player.getId().toString(), "1"});
		
		if (cursor.moveToFirst()) {
			do {
				DatabaseUtils.dumpCurrentRow(cursor);
			} while (cursor.moveToNext());
		}
		
		return items;
	}
	
}
