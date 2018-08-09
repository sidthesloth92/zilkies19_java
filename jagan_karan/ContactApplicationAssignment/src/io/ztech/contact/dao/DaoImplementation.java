package io.ztech.contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contact.bean.Details;
import io.ztech.contact.constants.*;;

public class DaoImplementation {
	public static final Logger logger = Logger.getLogger("SqlStatements");
	static Scanner input = new Scanner(System.in);

	public static void display(Details det, int id) {

		System.out.println("ID:  " + id + "\n" + "FIRSTNAME:  " + det.getFirstName() + "\n" + "LASTNAME:  " + det.getLastName()
				+ "\n" + "HOMENUMBER:  " + det.getHomeNumber() + "\n" + "OFFICENUMBER:" + det.getOfficeNumber() + "\n"
				+ "MOBILENUMBER:" + det.getMobileNumber() + "\n" + "EMAIL  :" + det.getEmail() + "\n");

	}

	public static void updateEmailDetails(String email, String name) {
		Connection con = DatabaseConfig.getConnection();
		try {
			con.setAutoCommit(false);
			PreparedStatement statement1 = con.prepareStatement(SqlConstantQueries.SELECTPERSON);
			statement1.setString(1, name);
			ResultSet rs = statement1.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("person_id");

			}
			PreparedStatement statement2 = con.prepareStatement(SqlConstantQueries.UPDATEEMAIL);
			statement2.setString(1, email);
			statement2.setInt(2, id);
			statement2.executeUpdate();
			logger.info(ConstantDisplayStatements.RECORDUPDATE + name);
			con.commit();

		} catch (Exception e) {
			System.out.println(e);
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void updateDetails(String number, String name, int option) {
		Connection con = DatabaseConfig.getConnection();
		try {
			con.setAutoCommit(false);
			PreparedStatement statement1 = con.prepareStatement(SqlConstantQueries.SELECTPERSON);
			statement1.setString(1, name);
			ResultSet rs = statement1.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("person_id");

			}
			PreparedStatement statement2 = con.prepareStatement(SqlConstantQueries.UPDATECONTACT);
			statement2.setString(1, number);
			statement2.setInt(2, id);
			statement2.setInt(3, option);
			statement2.executeUpdate();
			logger.info(ConstantDisplayStatements.RECORDUPDATE + name);
			con.commit();
		} catch (Exception e) {
			System.out.println(e);
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void insertDetails(Details temp) {
		Connection con = DatabaseConfig.getConnection();
		try {
			con.setAutoCommit(false);
			PreparedStatement statement1 = con.prepareStatement(SqlConstantQueries.INSERTPERSON);
			statement1.setString(1, temp.getFirstName());
			statement1.setString(2, temp.getLastName());
			statement1.executeUpdate();
			ResultSet res = statement1.executeQuery(SqlConstantQueries.MAX);
			int id = 0;
			while (res.next()) {
				id = res.getInt("max");
			}
			PreparedStatement statement2 = con.prepareStatement(SqlConstantQueries.INSERTOFFICE);
			statement2.setInt(1, id);
			statement2.setInt(2, 1);
			statement2.setString(3, temp.getOfficeNumber());
			statement2.executeUpdate();
			PreparedStatement statement3 = con.prepareStatement(SqlConstantQueries.INSERTHOME);
			statement3.setInt(1, id);
			statement3.setInt(2, 2);
			statement3.setString(3, temp.getHomeNumber());
			statement3.executeUpdate();
			PreparedStatement statement4 = con.prepareStatement(SqlConstantQueries.INSERTMOBILE);
			statement4.setInt(1, id);
			statement4.setInt(2, 3);
			statement4.setString(3, temp.getMobileNumber());
			statement4.executeUpdate();
			PreparedStatement statement5 = con.prepareStatement(SqlConstantQueries.INSERTEMAIL);
			statement5.setInt(1, id);
			statement5.setString(2, temp.getEmail());
			statement5.executeUpdate();
			logger.info(ConstantDisplayStatements.ADDSUCESS);
			con.commit();
		} catch (Exception e) {
			System.out.println(e);
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void selectDetails(int choice) {
		Connection con = DatabaseConfig.getConnection();
		Details det=new Details();
		if (choice == 1) {
			try {
				con.setAutoCommit(false);
				PreparedStatement statement1 = con.prepareStatement(SqlConstantQueries.SORTASC);
				ResultSet rs = statement1.executeQuery();
				int id = 0;
				while (rs.next()) {
					id = rs.getInt("person_id");
					det.setFirstName(rs.getString("firstname"));
					det.setLastName(rs.getString("lastname"));
					PreparedStatement statement2 = con.prepareStatement(SqlConstantQueries.SELCONTACTID);
					statement2.setInt(1, id);
					ResultSet res = statement2.executeQuery();
					while (res.next()) {
						int contact_id = res.getInt("contact_id");
						if (contact_id == 1)
							det.setOfficeNumber(res.getString("number"));
						else if (contact_id == 2)
							det.setHomeNumber(res.getString("number")) ;
						else
							det.setMobileNumber(res.getString("number"));;
					}
					PreparedStatement statement3 = con.prepareStatement(SqlConstantQueries.SELEMAIL);
					statement3.setInt(1, id);
					ResultSet res1 = statement3.executeQuery();
					while (res1.next()) {
						det.setEmail(res1.getString("email"));
					}
					display(det, id);
				}
				con.commit();
			} catch (Exception e) {
				System.out.println(e);
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {
				con.setAutoCommit(false);
				PreparedStatement statement1 = con.prepareStatement(SqlConstantQueries.SORTDESC);
				ResultSet rs = statement1.executeQuery();
				int id = 0;
				while (rs.next()) {
					id = rs.getInt("person_id");
					det.setFirstName(rs.getString("firstname"));
					det.setLastName(rs.getString("lastname"));
					PreparedStatement statement2 = con.prepareStatement(SqlConstantQueries.SELCONTACTID);
					statement2.setInt(1, id);
					ResultSet res = statement2.executeQuery();
					while (res.next()) {
						int contact_id = res.getInt("contact_id");
						if (contact_id == 1)
							det.setOfficeNumber(res.getString("number"));
						else if (contact_id == 2)
							det.setHomeNumber(res.getString("number")) ;
						else
							det.setMobileNumber(res.getString("number"));;
					}
					PreparedStatement statement3 = con.prepareStatement(SqlConstantQueries.SELEMAIL);
					statement3.setInt(1, id);
					ResultSet res1 = statement3.executeQuery();
					while (res1.next()) {
						det.setEmail(res1.getString("email"));
					}
					display(det, id);
				}
				con.commit();
			} catch (Exception e) {
				System.out.println(e);
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void delete(String name) {
		Connection con = DatabaseConfig.getConnection();
		Details det = new Details();
		try {
			con.setAutoCommit(false);
			PreparedStatement statement1 = con.prepareStatement(SqlConstantQueries.SELDETAILPERSON);
			statement1.setString(1, name);
			ResultSet rs = statement1.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("person_id");
				det.setFirstName(rs.getString("firstname"));
				det.setLastName(rs.getString("lastname")); ;
				PreparedStatement statement2 = con.prepareStatement(SqlConstantQueries.SELCONTACTID);
				statement2.setInt(1, id);
				ResultSet res = statement2.executeQuery();
				while (res.next()) {
					int contact_id = res.getInt("contact_id");
					if (contact_id == 1)
						det.setOfficeNumber(res.getString("number"));
					else if (contact_id == 2)
						det.setHomeNumber(res.getString("number")) ;
					else
						det.setMobileNumber(res.getString("number"));;
				}
				PreparedStatement statement3 = con.prepareStatement(SqlConstantQueries.SELEMAIL);
				statement3.setInt(1, id);
				ResultSet res1 = statement3.executeQuery();
				while (res1.next()) {
					det.setEmail(res1.getString("email"));
				}
				display(det, id);
			}
			logger.info(ConstantDisplayStatements.PERSONIDTODELETE);
			id = input.nextInt();
			PreparedStatement statement2 = con.prepareStatement(SqlConstantQueries.DELETE);
			statement2.setInt(1, id);
			statement2.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println(e);
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
