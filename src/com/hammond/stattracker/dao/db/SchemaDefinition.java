package com.hammond.stattracker.dao.db;

public class SchemaDefinition {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Statistics.db";

	
	public static final String ID = "id";
		
	
	public static final String TABLE_NAME_PLAYER = "player";
    public static final String COLUMN_TEAM = "team_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME  = "last_name";
    public static final String COLUMN_JERSEY_NUMBER = "jersey_number";
	
    
	public static String getCreatePlayerTableSql(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("CREATE TABLE ").append(TABLE_NAME_PLAYER);
		buffer.append("(");
			buffer.append(ID).append(" INTEGER PRIMARY KEY").append(",");
			buffer.append(COLUMN_TEAM).append(" INTEGER ").append(",");
			buffer.append(COLUMN_FIRST_NAME).append(" TEXT").append(",");
			buffer.append(COLUMN_LAST_NAME).append(" TEXT").append(",");
			buffer.append(COLUMN_JERSEY_NUMBER).append(" INTEGER");
			//TODO add FOREIGN KEY REFERENCES TEAM.TEAM_ID
		buffer.append(")");		
		return  buffer.toString();
	}
	
	
    public static final String TABLE_NAME_TEAM = "team";
    public static final String COLUMN_TEAM_NAME = "team_name";

	   

	public static String getCreateTeamTableSql(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("CREATE TABLE ").append(TABLE_NAME_TEAM);
		buffer.append("(");
			buffer.append(ID).append(" INTEGER PRIMARY KEY").append(",");
			buffer.append(COLUMN_TEAM_NAME).append(" TEXT");
		buffer.append(")");		
		return  buffer.toString();
	}
	
	
	

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