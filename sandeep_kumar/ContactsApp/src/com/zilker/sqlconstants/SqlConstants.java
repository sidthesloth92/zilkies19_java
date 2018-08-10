package com.zilker.sqlconstants;

public class SqlConstants {
	public static final String  INSERT_INTO_CONTACT_LIST="INSERT INTO contact_list (firstname,lastname)VALUES(?,?)";
	public static final String GET_MAX_ID="SELECT MAX(list_id) AS max FROM contact_list";
	public static final String INSERT_INTO_NUMBER_LIST="INSERT into number_list (list_id,type,contactdata)VALUES(?,?,?)";
	public static final String GET_ALL_DETAILS="SELECT * FROM contact_list,number_list WHERE contact_list.firstname=? AND contact_list.list_id=number_list.list_id";
	public static final String UPDATE_FIRSTNAME="UPDATE contact_list SET firstname=? WHERE list_id=?";
	public static final String UPDATE_LASTNAME="UPDATE contact_list SET lastname=? WHERE list_id=?";
	public static final String UPDATE_NUMBER_LIST="UPDATE number_list SET contactdata=? WHERE num_id=?";
	public static final String DELETE_NAME="DELETE FROM contact_list WHERE list_id=?";
	public static final String DELETE_NUMBER_LIST="DELETE FROM number_list WHERE num_id=?";
	public static final String FIND_BY_ID="SELECT type FROM number_list WHERE num_id=?";
	public static final String SORT_BY_FNAME="SELECT * FROM contact_list,number_list WHERE contact_list.list_id=number_list.list_id ORDER BY contact_list.firstname";
	public static final String SORT_BY_LNAME="SELECT * FROM contact_list,number_list WHERE contact_list.list_id=number_list.list_id ORDER BY contact_list.lastname";
}
