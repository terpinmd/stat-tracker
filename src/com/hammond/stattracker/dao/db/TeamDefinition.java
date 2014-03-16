package com.hammond.stattracker.dao.db;

public class TeamDefinition {

	
    public static final String TABLE_NAME_TEAM = "team";
    public static final String COLUMN_TEAM_NAME = "team_name";	
	public static final String ID = "id";	
	   

	public static String getCreateTeamTableSql(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("CREATE TABLE ").append(TABLE_NAME_TEAM);
		buffer.append("(");
			buffer.append("id").append(" INTEGER PRIMARY KEY").append(",");
			buffer.append(COLUMN_TEAM_NAME).append(" TEXT");
		buffer.append(")");		
		return  buffer.toString();
	}
	
}