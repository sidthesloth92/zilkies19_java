package com.zilker.constants;

public class SqlConstants {

	public final static String LOGIN = "select * from users where email = ? and pass_word = ?";
	
	public final static String GET_ROLE = "select * from role where role_id = ?";
	
	public final static String ADD_NORMAL_USER = "insert into users(user_name,pass_word,email,phone_no) values(?,?,?,?)";
	
	public final static String STORE_LOGIN_TIME = "insert into users_login(user_id) values(?)";
	
	//admin queries	
	
	public final static String VIEW_ADMIN_HISTORY = "select * from conference_history where status = ? and approver_id = ?";
	
	public final static String VIEW_ALL_ADMIN_HISTORY = "select * from conference_history where status = ?";
	
	public final static String VIEW_ALL_REQUESTS = "select * from conference_history where status = ? and from_date >= ?";
	
	public final static String SELECT_USER = "select * from users where user_id = ?";
	
	public final static String SELECT_ALL_HALL = "select * from hall";
	
	public final static String SELECT_HALL = "select * from hall where hall_id = ?";
	
	public final static String SELECT_MAX_HALL_ID = "select max(hall_id) as hall_id from hall";
	
	public final static String ADD_HALL = "insert into hall(hall_id,hall_name,hall_size) values( ? , ? , ? )";
	
	public final static String ADD_FACILITY = "insert into hall_facility(hall_id,facility_id) values( ? , ?)";
	
	public final static String FACILITY_LIST = "select * from facility";
	
	public final static String FACILITY_WISE_ID = "select * from facility where facility_id = ?";
	
	public final static String HALL_WISE_FACILITY = "select * from hall_facility where hall_id = ?";
	
	public final static String HALL_WITH_FACILITIES = "select h.hall_id, h.hall_name, h.hall_size, f.facility_id,f.facility_name from hall h, facility f, hall_facility hall where h.hall_id = hall.hall_id and f.facility_id = hall.facility_id";
	
	public final static String VIEW_REQUEST_BY_ID = "select * from conference_history where status = ? and conference_id = ? and from_date >=?";
	
	public final static String UPDATE_REQUEST = "update conference_history set approver_id = ?, status = ? where conference_id = ?";
	
	public final static String VIEW_HISTORY = "select * from conference_history where status != ? and from_date<=? order by conference_id desc";
	
	public final static String VIEW_UPCOMING = "select * from conference_history where status != ? and from_date >=? order by conference_id desc";
	
	//user
	
	public final static String AVAILABILITY = "select hal.hall_id as hall_id, hal.hall_name as hall_name, hal.hall_size as hall_size, conference.status as status,conference.from_date as from_date,conference.to_date as to_date,conference.from_time as from_time,conference.to_time as to_time  from hall hal,conference_history conference where hal.hall_id = conference.hall_id and conference.hall_id = ?";
	
	public final static String CHECK_DATES = "SELECT dates.hall_id as hall_id, dates.conference_id as conference_id, history.from_time as from_time, history.to_time as to_time,dates.conference_date as date ,halls.hall_name as hall_name,halls.hall_size FROM conf_date dates,conference_history history,hall halls where halls.hall_id = history.hall_id and halls.hall_id=dates.hall_id and history.conference_id=dates.conference_id and dates.conference_date between ? and ? and history.status =? and halls.hall_id = ?";
	
	public final static String GET_CONFERENCE_BY_DATE = "select history.hall_id, dates.conference_date as date, history.from_time as from_time, history.to_time as to_time from conference_history history,conf_date dates where history.conference_id = dates.conference_id and history.hall_id = ? and history.status = ? and dates.conference_date between ? and ?";
	
	public final static String GET_MAX_CONFERENCE_ID = "select max(conference_id) as conference_id from conference_history";
	
	public final static String ADD_HISTORY = "insert into conference_history(conference_name,hall_id,user_id,from_date,to_date,from_time,to_time,status) values(?,?,?,?,?,?,?,?)";

	public final static String CONFERENCE_DATE = "insert into conf_date(conference_id,conference_date,hall_id) values(?,?,?)";
	
	public final static String CONFERENCE_REQUEST_BY_USER_ID = "select history.conference_id as conference_id, halls.hall_id as hall_id ,halls.hall_name as hall_name, history.conference_name as conference_name, history.from_time as from_time,history.to_time as to_time,history.from_date as from_date,history.to_date as to_date,history.status as status from hall halls,conference_history history where halls.hall_id = history.hall_id and history.user_id= ? and from_date>=?";
	
	public final static String VIEW_HISTORY_BY_USER_ID = "select * from conference_history where status != ? and user_id = ? and from_date>=? order by conference_id desc";
	
	public final static String LIST_BY_HALL_WISE_FACILITY = "select halls.hall_id as hall_id,halls.hall_size as hall_size ,halls.hall_name as hall_name, h_facility.facility_id as facility_id, f.facility_name as facility_name from hall halls,hall_facility h_facility,facility f where halls.hall_id = h_facility.hall_id and f.facility_id = h_facility.facility_id and halls.hall_id = ?";
	
}
