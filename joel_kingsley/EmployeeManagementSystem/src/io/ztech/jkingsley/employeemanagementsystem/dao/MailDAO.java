package io.ztech.jkingsley.employeemanagementsystem.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.jkingsley.employeemanagement.constants.Fields;
import io.ztech.jkingsley.employeemanagement.constants.Query;
import io.ztech.jkingsley.employeemanagement.utils.DBUtils;
import io.ztech.jkingsley.employeemanagement.utils.PersistenceException;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Mail;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.MailType;

public class MailDAO {
	/*
	 * BigInteger mail_id; BigInteger emp_id; String mail_address; MailType
	 * mail_type;
	 */

	public boolean addMailAddresses(ArrayList<Mail> mailAddresses) throws PersistenceException {

		Connection connection = DBUtils.getConnection();

		try {

			for (Mail mailAddress : mailAddresses) {
				PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_MAIL_ADDRESS);
				preparedStatement.setString(1, mailAddress.getEmp_id().toString());
				preparedStatement.setString(2, mailAddress.getMail_address());
				preparedStatement.setString(3, mailAddress.getMail_type().toString());

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows == 0) {
					throw new PersistenceException("No mail addresses added");
				}
			}

		} catch (SQLException e) {
			throw new PersistenceException("Error while adding mail addresses", e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}

	public boolean addMailAddress(Mail mail) throws PersistenceException {
		Connection connection = DBUtils.getConnection();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_MAIL_ADDRESS);
			preparedStatement.setString(1, mail.getEmp_id().toString());
			preparedStatement.setString(2, mail.getMail_address());
			preparedStatement.setString(3, mail.getMail_type().toString());

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenceException("No mail addres added");
			}

		} catch (SQLException e) {
			throw new PersistenceException("Error while adding mail address", e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}

	public ArrayList<Mail> findMailAddressesOfID(BigInteger emp_id) throws PersistenceException {
		ArrayList<Mail> mails = new ArrayList<>();

		Connection connection = DBUtils.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.FIND_MAILS_OF_ID);
			preparedStatement.setString(1, emp_id.toString());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Mail mail = new Mail();

				mail.setEmp_id(emp_id);
				mail.setMail_id(BigInteger.valueOf(rs.getLong(Fields.MAIL_KEY_ID)));
				mail.setMail_address(rs.getString(Fields.MAIL_KEY_ADDRESS));
				mail.setMail_type(MailType.valueOf(rs.getString(Fields.MAIL_KEY_TYPE)));

				mails.add(mail);
			}

			return mails;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Error in finding all mails", e);
		} finally {
			DBUtils.closeConnection(connection);
		}
	}

	public boolean updateMailAddressOfID(Mail mail2) {
		
		return false;
	}
}
