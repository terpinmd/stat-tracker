package com.hammond.stattracker.dao.db;

public class GameSchema {

    public static final String TABLE_NAME_GAME = "game";
    public static final String COLUMN_GAME_TITLE = "title";
    public static final String COLUMN_GAME_DATE = "game_date";
    public static final String COLUMN_GAME_NOTES = "notes";
    public static final String COLUMN_TEAM = "team";
    public static final String COLUMN_MY_TEAM = "my_team_id";
    public static final String COLUMN_VS_TEAM = "vs_team_id";    
    
	public static String getCreateGameTableSql(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("CREATE TABLE ").append(TABLE_NAME_GAME);
		buffer.append("(");
			buffer.append("id").append(" INTEGER PRIMARY KEY").append(",");
			buffer.append(COLUMN_GAME_TITLE).append(" TEXT").append(",");
			buffer.append(COLUMN_GAME_DATE).append(" INTEGER").append(",");
			buffer.append(COLUMN_MY_TEAM).append(" INTEGER REFERENCES TEAM(id) ON DELETE CASCADE ").append(",");
			buffer.append(COLUMN_VS_TEAM).append(" INTEGER  REFERENCES TEAM(id) ON DELETE CASCADE ").append(",");
			buffer.append(COLUMN_GAME_NOTES).append(" TEXT");
		buffer.append(")");		 
		return  buffer.toString();
	}
}