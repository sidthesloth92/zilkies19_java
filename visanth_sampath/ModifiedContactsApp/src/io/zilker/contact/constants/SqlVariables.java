package io.zilker.contact.constants;

public class SqlVariables {
	public static final  String SELECT_WITH_NAMES = "select * from contact where first_name= ? and last_name= ?";
	public static final  String INSERT_NAMES = "insert into contact(first_name,last_name) values(?,?)";
	public static final  String INSERT_EMAIL = "insert into email(id,mail) values(?,?)";
	public static final  String INSERT_HOME = "insert into home_numbers(id,country_code,home_no,area_code) values(?,?,?,?)";
	public static final  String INSERT_MOBILE = "insert into mobile_no(id,mobile_no,country_code) values(?,?,?)";
	public static final  String INSERT_OFFICE = "insert into office_numbers(id,off_number,extension) values(?,?,?)";
	public static final  String DELETE_EMAIL = "delete from email where id= ?";
	public static final  String DELETE_HOME = "delete from home_numbers where id= ? ";
	public static final  String DELETE_OFFICE = "delete from office_numbers where id= ? ";
	public static final  String DELETE_MOBILE = "delete from mobile_no where id= ? ";
	public static final  String DELETE_CONTACT = "delete from contact where id= ? ";
	public static final  String SELECT_BY_FNAME = "Select * from contact order by first_name";
	public static final  String SELECT_FROM_EMAIL = "Select * from email where id = ?";
	public static final  String SELECT_FROM_HOME = "Select * from home_numbers where id = ?";
	public static final  String SELECT_FROM_MOBILE = "Select * from mobile_no where id = ?";
 	public static final  String SELECT_FROM_OFFICE = "Select * from office_numbers where id = ?";
 	public static final  String UPDATE_NAME = "update contact set first_name = ? ,last_name = ? where id =? ";
 	public static final  String UPDATE_MAIL = "update email set mail =? where table_id = ? ";
	public static final  String UPDATE_HOME = "update home_numbers set home_no =? , area_code = ? , country_code=? where table_id = ?";
	public static final  String UPDATE_MOBILE = "update mobile_no set mobile_no =? ,  country_code=? where table_id = ?";
 	public static final  String UPDATE_OFFICE = "update office_numbers set off_number =? ,  extension = ? where table_id = ? "; 			
 	public static final  String LAST_INSERTED = "select id from contact order by id desc";
 	public static final  String DELETE_SPECIFIC_EMAIL = "delete from email where table_id= ?";
	public static final  String DELETE_SPECIFIC_HOME = "delete from home_numbers where table_id= ? ";
	public static final  String DELETE_SPECIFIC_OFFICE = "delete from office_numbers where table_id= ? ";
	public static final  String DELETE_SPECIFIC_MOBILE = "delete from mobile_no where table_id= ? ";
	public static final  String SELECT_ALL_ROWS  = "select * from contact order by first_name,last_name";
}
