package io.ztech.carstats.constants;

public class SQLConstants {
	public static String SELECT_ALL_MAKE = "select * from make";
	public static String SELECT_ALL_CAR_TYPE = "select * from car_type";
	public static String SELECT_ALL_CAR = "select * from specification where car_id in(select car_id from car where make_id=? and car_type_id=?)";
	public static String DELETE_CAR = "delete from specification where car_id=?";
	public static String INSERT_CAR = "insert into specification (car_name,engine_type,cylinder,displacement,transmission,"
			+ "power,torque,fuel_capacity,wheelbase,kerb_weight,airbag,"
			+ "abs,drivetrain,price,car_status) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String INSERT_CAR_ID = "insert into car (car_id,make_id,car_type_id) values (?,?,?)";
	public static String INSERT_MAKE = "insert into make (make_name) values (?)";
	public static String SELECT_MAKE_ID = "select make_id from make where make_name=?";
	public static String INSERT_CAR_TYPE = "insert into car_type (car_type_name) values (?)";
	public static String SELECT_CAR_TYPE_ID = "select car_type_id from car_type where car_type_name=?";
	public static String SELECT_CAR_ID = "select car_id from specification order by car_id DESC LIMIT 1";
	public static String INSERT_USER = "insert into users (user_name,user_password,admin_status) values(?,?,'USER')";
	public static String INSERT_RATING = "insert into rating(car_id,user_name,rating,review) values (?,?,?,?)";
	public static String INSERT_REQUEST = "insert into request(car_id,user_name) values(?,?)";
	public static String SELECT_REQUEST_ID = "select request_id from request order by request_id DESC LIMIT 1";
	public static String APPROVE_CAR = "update specification set car_status='APPROVED' where car_id=?";
	public static String SELECT_ALL_REQUEST = "select * from request";
	public static String SELECT_CAR = "select * from specification where car_id in(select car_id "
			+ "from request where request_id=?)";
	public static String DELETE_REQUEST = "delete from request where request_id=?";
}
