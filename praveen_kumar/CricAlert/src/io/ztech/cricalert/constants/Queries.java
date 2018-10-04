package io.ztech.cricalert.constants;

public class Queries {
	private Queries() {}
	
	public static final String INSERT_USER = "insert into user (name, email, user_name, password) values (?, ?, ?, ?)";
	public static final String INSERT_PLAYER = "insert into player (team_id, first_name, last_name, user_id) values (?, ?, ?, ?)";
	public static final String INSERT_PLAYER_STATS = "insert into player_stats (runs_scored, balls_faced, wickets_taken, runs_given, overs, bat_flag, bowl_flag, fours, sixes, economy, strike_rate, match_id, player_id, team_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_TEAM = "insert into team (team_name, abbreviation, user_id) values (?, ?, ?)";
	public static final String INSERT_MATCH = "insert into matches (match_datetime, venue, team_1_id, team_2_id, status, user_id) values (?, ?, ?, ?, ?, ?)";
	public static final String INSERT_MATCH_STATS = "insert into match_stats (batting_team, bowling_team, team_a_score, team_b_score, team_a_wickets, team_b_wickets, overs, onstrike, offstrike, bowler, inning, match_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_BALL_STATS = "insert into ball_stats (match_id, team_batting, team_bowling, bowler_id, batsman_id, runs_given, wicket_taken) values (?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_LINE_UP = "insert into line_up (match_id, team_id, player_id) values (?, ?, ?)"; 
	
	public static final String FETCH_TEAM = "select team_id, abbreviation, team_name from team where team_id = ?";
	public static final String FETCH_TEAMS = "select team_id, abbreviation, team_name from team where user_id = ?";
	public static final String FETCH_TEAM_PLAYERS = "select team_id, player_id, first_name, last_name from player where team_id = ?";
	public static final String FETCH_PLAYERS = "select player_id, first_name, last_name from player where player_id = ?";
	public static final String FETCH_PLAYER = "select first_name, last_name, team_id from player where player_id = ?";
	public static final String FETCH_PLAYER_STATS = "select * from player_stats where match_id = ?";
	public static final String CHECK_PLAYER_STATS = "select * from player_stats where match_id = ? and player_id = ?";
	public static final String FETCH_USER_PLAYERS = "select player_id, first_name, last_name, team_id from player where user_id = ?";
	public static final String FETCH_MATCH = "select match_id, match_datetime, venue, team_1_id, team_2_id, status, match_result from matches where match_id = ?";
	public static final String FETCH_MATCH_STATS = "select * from match_stats where match_id = ?";
	public static final String FETCH_SCHEDULED_MATCH = "select count(*) from matches where user_id = ? and status = 'scheduled'";
	public static final String FETCH_MATCHES = "select match_id, match_datetime, venue, team_1_id, team_2_id, status, match_result from matches where user_id = ?";
	public static final String FETCH_USER = "select * from user where user_name = ?";
	public static final String FETCH_USER_ID = "select user_id from user where user_name = ?";
	public static final String FETCH_LINE_UP = "select player_id from line_up where match_id = ? and team_id = ?";
	public static final String FETCH_RECENT_TEAM_ID = "select team_id from team order by team_id desc limit 1";
	public static final String FETCH_RECENT_MATCH_ID = "select match_id from matches order by match_id desc limit 1";
	public static final String FETCH_RECENT_PLAYER_ID = "select player_id from player order by player_id desc limit 1";
	
	public static final String SEARCH_USER = "select count(*) from user where user_name = ?";
	public static final String SEARCH_TEAM = "select count(*) from team where team_id = ? and user_id = ?";
	public static final String SEARCH_PLAYER = "select count(*) from player where player_id = ? and user_id = ?";
	public static final String SEARCH_MATCH = "select count(*) from matches where match_id = ? and user_id = ?";
	
	public static final String UPDATE_TEAM_NAME = "update team set team_name = ? where team_id = ?";
	public static final String UPDATE_PLAYER = "update player set first_name = ?, last_name = ?, team_id = ? where player_id = ?";
	public static final String UPDATE_PLAYER_STATS = "update player_stats set runs_scored = ?, balls_faced = ?, wickets_taken = ?, runs_given = ?, overs = ?, bat_flag = ?, bowl_flag = ?, fours = ?, sixes = ?, economy = ?, strike_rate = ? where match_id = ? and player_id = ?";
	public static final String UPDATE_PLAYER_TEAM = "update player set team_id = ? where player_id = ?";
	public static final String UPDATE_PLAYER_FIRST_NAME = "update player set first_name = ? where player_id = ?";
	public static final String UPDATE_PLAYER_LAST_NAME = "update player set last_name = ? where player_id = ?";
	public static final String UPDATE_MATCH_DATE = "update matches set match_datetime = ? where match_id = ?";
	public static final String UPDATE_MATCH_TEAM_A = "update matches set team_1_id = ? where match_id = ?";
	public static final String UPDATE_MATCH_TEAM_B = "update matches set team_2_id = ? where match_id = ?";
	public static final String UPDATE_MATCH_STATUS = "update matches set status = ?, match_result = ? where match_id = ?";
	public static final String UPDATE_MATCH_STATS = "update match_stats set batting_team = ?, bowling_team = ?, team_a_score = ?, team_b_score = ?, team_a_wickets = ?, team_b_wickets = ?, overs = ?, onstrike = ?, offstrike = ?, bowler = ?, inning = ? where match_id = ?";
	
	public static final String ADD_PLAYER_TO_TEAM = "insert into player (team_id, first_name, last_name, user_id) values (?, ?, ?, ?)";
	
	public static final String REMOVE_PLAYER_TEAM = "update player set team_id = null where team_id = ?";
	public static final String DELETE_PLAYER = "delete from player where player_id = ?";
	public static final String DELETE_TEAM = "delete from team where team_id = ?";
}
