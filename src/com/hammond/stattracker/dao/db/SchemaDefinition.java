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
}