package io.ztech.contact_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import io.ztech.contact_app.bean.ContactDetail;
import io.ztech.contact_app.constants.ApplicationConstants;
import io.ztech.contact_app.constants.SqlConstants;
import io.ztech.contact_app.dbutil.DbConnection;

public class ViewContactDao {
	Logger log = Logger.getLogger("ViewContactDao.class");
	Connection conn = DbConnection.getConnection();
	public static HashMap<String, ArrayList<String>> idList = new HashMap<>();

	public void viewAllContact(int sortChoice) {
		PreparedStatement ps = null;
		ResultSet rs1 = null;
		String id;
		int count = 1;
		try {
			conn.setAutoCommit(false);
			if (sortChoice == 1)
				ps = conn.prepareStatement(SqlConstants.SORT_FIRST_NAME);
			else
				ps = conn.prepareStatement(SqlConstants.SORT_LAST_NAME);
			rs1 = ps.executeQuery();
			showContact(rs1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs1, ps, conn);
		}

	}

//view specific contact
	public HashMap<String, ArrayList<String>> viewSpecificContact(ContactDetail contact) {
		PreparedStatement ps1, ps2;
		ResultSet rs1, rs2;
		try {
			ps1 = conn.prepareStatement(SqlConstants.VIEW_SPECIFIC_CONTACT);
			ps1.setString(1, contact.getFirstName());
			ps1.setString(2, contact.getLastName());
			rs1 = ps1.executeQuery();
			if (rs1.isBeforeFirst() == false) {
				log.info(ApplicationConstants.NOT_FOUND);
				return null;
			}
			showContact(rs1);
			return idList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return idList;

	}

	public void showContact(ResultSet rs1) {
		String id;
		int count = 1;
		ArrayList<String> contact_idList = new ArrayList<>();
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		try {
			while (rs1.next()) {
				id = rs1.getString("contact_id");
				contact_idList.add(id);
				System.out.print(count + ".");
				showName(rs1);
				showMobileNumber(id, ps2, rs2);
				showOffice_number(id, ps2, rs2);
				showHomeNumber(id, ps2, rs2);
				showEmail(id, ps2, rs2);
				System.out.println();
				count++;
			}
			idList.put("contact_id", contact_idList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(rs2, ps2, conn);
		}
	}

	public void showName(ResultSet rs1) throws SQLException {

		System.out.println(ApplicationConstants.CONTACT_ID + rs1.getString("contact_id"));
		System.out.println(ApplicationConstants.PFIRST_NAME + rs1.getString("first_name"));
		System.out.println(ApplicationConstants.PLAST_NAME + rs1.getString("last_name"));

	}

	public void showOffice_number(String id, PreparedStatement ps2, ResultSet rs2) {
		ArrayList<String> office_id = new ArrayList<>();
		int count = 1;
		try {
			ps2 = conn.prepareStatement(SqlConstants.VIEW_OFFICE_NUMBER);

			ps2.setString(1, id);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				if (count == 1) {
					System.out.println(ApplicationConstants.POFFICE_NUM.trim());
				}
				System.out.print(count + ". ");
				System.out.print(ApplicationConstants.OFFICE_ID + rs2.getString("office_numberId"));
				System.out.print(ApplicationConstants.POFFICE_NUM + rs2.getString("extension_number") + "-"
						+ rs2.getString("office_number"));
				System.out.println();
				office_id.add(rs2.getString("office_numberId"));
				count++;
			}
			idList.put("office_id", office_id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showMobileNumber(String id, PreparedStatement ps2, ResultSet rs2) {
		int count = 1;
		ArrayList<String> mobile_id = new ArrayList<>();
		try {
			ps2 = conn.prepareStatement(SqlConstants.VIEW_MOBILE_NUMBER);
			ps2.setString(1, id);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				if (count == 1) {
					System.out.println(ApplicationConstants.PMOBILE__NUM.trim());
				}
				System.out.print(count + ". ");
				System.out.print(ApplicationConstants.MOBILE_ID + rs2.getString("mobile_id"));
				System.out.print(ApplicationConstants.PMOBILE__NUM + rs2.getString("country_code") + "-"
						+ rs2.getString("mobile_number") + "\n");
				mobile_id.add(rs2.getString("mobile_id"));
				count++;
			}
			idList.put("mobile_id", mobile_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showHomeNumber(String id, PreparedStatement ps2, ResultSet rs2) {
		ArrayList<String> home_id = new ArrayList<>();
		int count = 1;
		try {
			ps2 = conn.prepareStatement(SqlConstants.VIEW_HOME_NUMBER);
			ps2.setString(1, id);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				if (count == 1) {
					System.out.println(ApplicationConstants.PHOME_NUM.trim());
				}
				System.out.print(count + ". ");
				System.out.print(ApplicationConstants.HOME_ID + rs2.getString("home_id"));
				System.out.print(ApplicationConstants.PHOME_NUM + rs2.getString("area_code") + "-"
						+ rs2.getString("home_number"));
				System.out.println();
				home_id.add(rs2.getString("home_id"));
				count++;
			}
			idList.put("home_id", home_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showEmail(String id, PreparedStatement ps2, ResultSet rs2) {
		ArrayList<String> mail_id = new ArrayList<>();
		int count = 1;
		try {
			ps2 = conn.prepareStatement(SqlConstants.VIEW_EMAIL);
			ps2.setString(1, id);
			rs2 = ps2.executeQuery();

			while (rs2.next()) {
				if (count == 1) {
					System.out.println(ApplicationConstants.PEMAIL.trim());
				}
				System.out.print(count + ".");
				System.out.print(ApplicationConstants.MAIL_ID + rs2.getString("email_id"));
				System.out.print(ApplicationConstants.PEMAIL + rs2.getString("mail"));
				System.out.println();
				mail_id.add(rs2.getString("email_id"));
				count++;
			}
			idList.put("email", mail_id);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
