package io.ztech.contacts.constants;

//DB Query constants
public class QueryConstants {
	
	public final String INSERTCONTACTDETAILS = "insert into contactDetails(first_name,last_name) values(?,?)";
	public final String SELECTRECENTCID = "select c_id from contactDetails order by c_id desc limit 1";
	public final String SELECTMID = "select m_id from mobileDetails where c_id = ? order by m_id desc limit 1";
	public final String INSERTMOBILEDETAILS = "insert into mobileDetails values(?,?,?,?)";
	public final String SELECTHID = "select h_id from homeDetails where c_id = ? order by h_id desc limit 1";
	public final String INSERTHOMEDETAILS = "insert into homeDetails values(?,?,?,?,?)";
	public final String SELECTOID = "select o_id from officeDetails where c_id = ? order by o_id desc limit 1";
	public final String INSERTOFFICEDETAILS = "insert into officeDetails values(?,?,?,?,?,?)";
	public final String SELECTEID = "select e_id from emailDetails where c_id = ? order by e_id desc limit 1";
	public final String INSERTEMAILDETAILS = "insert into emailDetails values(?,?,?)";
	public final String SELECTCONTACTS = "select * from contactDetails where first_name like ? or last_name like ?";
	public final String SELECTMOBILEDETAILS = "select * from contactDetails natural join mobileDetails where first_name like ? or last_name like ? or mob_num like ?";
	public final String SELECTOFFICEDETAILS = "select * from contactDetails natural join officeDetails where first_name like ? or last_name like ? or off_num like ?";
	public final String SELECTHOMEDETAILS = "select * from contactDetails natural join homeDetails where first_name like ? or last_name like ? or home_num like ?";
	public final String SELECTEMAILDETAILS = "select * from contactDetails natural join emailDetails where first_name like ? or last_name like ? or email_id like ?";
	public final String SELECTCONTACTDETAILS = "select * from contactDetails";
	public final String SELECTCONTACT = "select * from contactDetails where c_id = ?";
	public final String SELECTMOBNUMBERS = "select country_code,mob_num from mobileDetails where c_id = ?";
	public final String SELECTOFFNUMBERS = "select country_code,area_code,off_num,extn from officeDetails where c_id = ?";
	public final String SELECTHOMENUMBERS = "select country_code,area_code,home_num from homeDetails where c_id = ?";
	public final String SELECTEMAILIDS = "select email_id from emailDetails where c_id = ?";
	public final String DELETECONTACT = "delete from contactDetails where c_id = ?";
	public final String DELETEMOBNUMBER = "delete from mobileDetails where c_id = ? and m_id = ?";
	public final String DELETEOFFNUMBER = "delete from officeDetails where c_id = ? and o_id = ?";
	public final String DELETEHOMENUMBER = "delete from homeDetails where c_id = ? and h_id = ?";
	public final String DELETEEMAILID = "delete from emailDetails where c_id = ? and e_id = ?";
	public final String UPDATEFIRSTNAME = "update contactDetails set first_name = ? where c_id = ?";
	public final String UPDATELASTNAME = "update contactDetails set last_name = ? where c_id = ?";
	public final String UPDATEOFFICENUMBER = "update officeDetails set off_num = ?, extn = ? , country_code = ? , area_code = ? where c_id = ? and o_id = ?";
	public final String UPDATEMOBILENUMBER = "update mobileDetails set mob_num = ?, country_code = ? where c_id = ? and m_id = ?";
	public final String UPDATEHOMENUMBER = "update homeDetails set home_num = ?, country_code = ?, area_code = ? where c_id = ? and h_id = ?";
	public final String UPDATEEMAILID = "update emailDetails set email_id = ? where c_id = ? and e_id = ?";
	
}
