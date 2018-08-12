package io.ztech.contactapp.constants;

//=========================================================================================================================================
//QUERY CONSTANTS INTERFACE - QUERY CONSTANT STRINGS
//=========================================================================================================================================
public interface QueryConstants {
	public final String INSERT_INTO_CONTACT_DETAILS = "insert into contactDetails(first_name,last_name) values(?,?)";
	public final String INSERT_INTO_MOBILE_DETAILS = "insert into mobileDetails(c_id,mob_num,country_code) values(?,?,?)";
	public final String INSERT_INTO_HOME_DETAILS = "insert into homeDetails(c_id,home_num,country_code,area_code) values(?,?,?,?)";
	public final String INSERT_INTO_OFFICE_DETAILS = "insert into officeDetails(c_id,off_num,extn,country_code,area_code) values(?,?,?,?,?)";
	public final String INSERT_INTO_EMAIL_DETAILS = "insert into emailDetails(c_id,email_id) values(?,?)";
	public final String SELECT_RECENT_CID = "select c_id from contactDetails order by c_id desc limit 1";
	public final String SELECT_CONTACT_DETAILS = "select * from contactDetails";
	public final String SELECT_MOBILE_NUMBERS = "select m_id,country_code,mob_num from mobileDetails where c_id = ?";
	public final String SELECT_OFFICE_NUMBERS = "select o_id,country_code,area_code,off_num,extn from officeDetails where c_id = ?";
	public final String SELECT_HOME_NUMBERS = "select h_id,country_code,area_code,home_num from homeDetails where c_id = ?";
	public final String SELECT_EMAIL_IDS = "select e_id,email_id from emailDetails where c_id = ?";
	public final String SEARCH_CONTACTS = "select * from contactDetails where first_name like ? or last_name like ?";
	public final String DELETE_CONTACT = "delete from contactDetails where c_id = ?";
	public final String DELETE_MOBILE_NUMBER = "delete from mobileDetails where c_id = ? and m_id = ?";
	public final String DELETE_OFFICE_NUMBER = "delete from officeDetails where c_id = ? and o_id = ?";
	public final String DELETE_HOME_NUMBER = "delete from homeDetails where c_id = ? and h_id = ?";
	public final String DELETE_EMAIL_ID = "delete from emailDetails where c_id = ? and e_id = ?";
	public final String UPDATE_FIRST_NAME = "update contactDetails set first_name = ? where c_id = ?";
	public final String UPDATE_LAST_NAME = "update contactDetails set last_name = ? where c_id = ?";
	public final String UPDATE_OFFICE_NUMBER = "update officeDetails set off_num = ?, extn = ? , country_code = ? , area_code = ? where c_id = ? and o_id = ?";
	public final String UPDATE_MOBILE_NUMBER = "update mobileDetails set mob_num = ?, country_code = ? where c_id = ? and m_id = ?";
	public final String UPDATE_HOME_NUMBER = "update homeDetails set home_num = ?, country_code = ?, area_code = ? where c_id = ? and h_id = ?";
	public final String UPDATE_EMAIL_ID = "update emailDetails set email_id = ? where c_id = ? and e_id = ?";
}
