package com.zilkeesaro.constants;

public class SqlConstants {
	
	public final static String SELECT_MAX="select max(contact_id) from Contacts";

	public final static String ADD_CONTACTS="insert into Contacts(contact_id,first_name,last_name,email) values(?,?,?,?)";
	
	public final static String ADD_PHONE="insert into Phone(contact_id,phone_type,phone_no) values(?,?,?)";
	
	public final static String SELECT_TYPE="select * from Phone_Type where type_name=?";
	
	public final static String SORT_BY_ID="select * from Contacts order by contact_id asc";
	
	public final static String SORT_BY_FIRST_NAME="select * from Contacts order by first_name asc";
	
	public final static String SORT_BY_LAST_NAME="select * from Contacts order by last_name asc";
	
	public final static String SORT_BY_EMAIL="select * from Contacts order by email asc";
	
	public final static String LIST_BY_ID="select phone.phone_no as phone_no,phone.phone_type as phone_type from Contacts contacts,Phone phone,Phone_Type type where contacts.contact_id=phone.contact_id and phone.phone_type=type.phone_type and contacts.contact_id=?";;
	
	public final static String UPDATE_FIRST_LAST="update Contacts set first_name = ? ,last_name= ? where contact_id= ?";
	
	public final static String UPDATE_NUMBERS="update Phone set phone_no = ? where contact_id= ? and phone_type = ?";
	
	public final static String UPDATE_EMAIL="update Contacts set email = ? where contact_id = ?";
	
	public final static String DEL_CONTACT="delete from Contacts where contact_id = ?";
	
	public final static String DEL_PHONE="delete from Phone where contact_id = ?";
}

