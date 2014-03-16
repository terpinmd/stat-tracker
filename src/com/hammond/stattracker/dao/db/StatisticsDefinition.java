package com.hammond.stattracker.dao.db;

public class StatisticsDefinition {

	public static final String ID = "id";
	

	public static final String TABLE_NAME_STATISTIC = "statistic";
	public static final String COLUMN_NAME_STATISTIC_SPORT  = "sport";
	public static final String COLUMN_NAME_STATISTIC_NAME = "statistic_name";
	
	public static String getCreateStatisticsTableSql(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("CREATE TABLE ").append(TABLE_NAME_STATISTIC);
		buffer.append("(");
			buffer.append(ID).append(" INTEGER PRIMARY KEY").append(",");
			buffer.append(COLUMN_NAME_STATISTIC_SPORT).append(" TEXT").append(",");
			buffer.append(COLUMN_NAME_STATISTIC_NAME).append(" TEXT ");
		buffer.append(")");		
		return  buffer.toString();
	}
	
	
	public static final String TABLE_NAME_GAME_STATISTICS = "game_statistics";
	public static final String COLUMN_NAME_GAME_STATISTICS_GAME_ID = "game_id";
	public static final String COLUMN_NAME_GAME_STATISTICS_PLAYER_ID = "player_id";
	public static final String COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID = "statistic_id";
	public static final String COLUMN_NAME_GAME_STATISTICS_STATISTICS_VALUE = "statistic";
	
	public static String getCreateGameStatisticsTableSql(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("CREATE TABLE ").append(TABLE_NAME_GAME_STATISTICS);
		buffer.append("(");
			buffer.append(ID).append(" INTEGER PRIMARY KEY").append(",");
			buffer.append(COLUMN_NAME_GAME_STATISTICS_GAME_ID).append(" INTEGER ").append(",");
			buffer.append(COLUMN_NAME_GAME_STATISTICS_PLAYER_ID).append(" INTEGER ").append(",");
			buffer.append(COLUMN_NAME_GAME_STATISTICS_STATISTICS_ID).append(" INTEGER").append(",");
			buffer.append(COLUMN_NAME_GAME_STATISTICS_STATISTICS_VALUE).append(" INTEGER");
		buffer.append(")");		
		return  buffer.toString();
	}
}