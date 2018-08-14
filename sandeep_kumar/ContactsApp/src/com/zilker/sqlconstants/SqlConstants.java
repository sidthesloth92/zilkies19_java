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
	public static final String INVALID_INPUT="Invalid Input. Please Re-enter";
	public static final String MAIN_MENU="1.Add \n2.Update \n3.Delete \n4.Display \n5.Exit";
	public static final String UPDATE_MENU="Choose an Option to Update\n 1.FirstName \n2.LastName \n3.Others \n4.Exit";
	public static final String DELETE_MENU="Choose an Option to Delete\n 1.FirstName \n2.LastName \n3.Others \n4.Exit";
	public static final String DISPLAY_MENU="1.Sort by FirstName \n2.Sort By LastName \n3.Exit";
	public static final String VALID_OPTION="Enter Valid Option!";
	public static final String UPDATION_SUCCESS="Successfully Updated!";
	public static final String UPDATION_ERROR="Error in Updation!";
	public static final String DELETION_SUCCESS="Successfully Deleted!";
	public static final String DELETION_ERROR="Error in Deletion!";
	public static final String ID_FIRSTNAME="Enter id of the Firstname to be ";
	public static final String ID_LASTNAME="Enter id of the Lastname to be ";
	public static final String ID_FIELD="Enter id of the Field to be ";
}
