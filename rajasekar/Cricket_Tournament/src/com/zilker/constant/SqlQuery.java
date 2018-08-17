package com.zilker.constant;

public class SqlQuery {
	public static final String LOGIN = "select email,password from user where email=? AND password=?";
	public static final String VIEW_TOURNAMENT = "select * from add_tournament";
	public static final String INSERT_TEAM = "insert into team (team_name,tournament_id,created_by,updated_by,email) VALUES (?,?,?,?,?)";
	public static final String TEAM_ID = "select team_id from team";
	public static final String INSERT_PLAYER = "insert into player (player_name,role,team_id,created_by,updated_by) VALUES (?,?,?,?,?)";
	public static final String INSERT_USER = "insert into user (first_name,last_name,password,age,mobile,email,created_by,updated_by) VALUES (?,?,?,?,?,?,?,?)";
	public static final String ADMIN_LOGIN = "select email,password from admin where email=? AND password=?";
	public static final String INSERT_ADMIN = "insert into admin (first_name,last_name,password,age,mobile,email,created_by,updated_by) VALUES (?,?,?,?,?,?,?,?)";
	public static final String INSERT_TOURNAMENT = "insert into add_tournament (tournament_name,format,created_by,updated_by) VALUES (?,?,?,?)";
	public static final String VIEW_TEAMS = "select team_id,team_name from team where tournament_id=? AND email=?";
	public static final String USER_TEAMS = "select team_id,team_name from team where tournament_id=?";
	public static final String TEAMS = "select team_id,team_name from team where email=?";
	public static final String VIEW_TEAMID = "select team_id from team where team_name=?";
	public static final String INSERT_SCHEDULE = "insert into schedule (team1_id,team2_id,matches,created_by,updated_by,tournament_id) VALUES (?,?,?,?,?,?)";
	public static final String DELETE_SCHEDULE = "delete from schedule where tournament_id=?";
	public static final String TEAM_NAME_VERIFICATION = "select team_name from team where tournament_id=?";
	public static final String UPDATE_TEAM_NAME = "update team SET team_name=? where team_name=? AND tournament_id=?";
	public static final String GET_TEAM_ID = "select team_id from team where team_name=?";
	public static final String UPDATE_PLAYER = "update player set player_name=?,role=? where team_id=? AND player_id=?";
	public static final String VIEW_SCHEDULE = "select * from schedule where tournament_id=?";
	public static final String CHECK_TEAM_NAME = "select * from team where team_name=?";
	public static final String PLAYER_DETAILS = "select player_id,player_name from player where team_id=?";
}