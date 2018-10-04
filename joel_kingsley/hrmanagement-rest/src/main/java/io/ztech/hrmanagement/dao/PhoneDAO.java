package io.ztech.hrmanagement.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.hrmanagement.beans.objects.Phone;
import io.ztech.hrmanagement.beans.types.PhoneType;
import io.ztech.hrmanagement.constants.Fields;
import io.ztech.hrmanagement.constants.Query;
import io.ztech.hrmanagement.constants.Titles;
import io.ztech.hrmanagement.utils.DBUtils;
import io.ztech.hrmanagement.utils.PersistenceException;

public class PhoneDAO {
	/*
	 * BigInteger phone_id; BigInteger emp_id; String phone_number; PhoneType
	 * phone_type;
	 */

	public boolean addPhoneNumbers(ArrayList<Phone> phoneNumbers) throws PersistenceException {

		Connection connection = DBUtils.getConnection();

		try {

			for (Phone phoneNumber : phoneNumbers) {
				PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_PHONE_NUMBER);
				preparedStatement.setString(1, phoneNumber.getEmp_id().toString());
				preparedStatement.setString(2, phoneNumber.getPhone_number());
				preparedStatement.setString(3, phoneNumber.getPhone_type().toString());

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows == 0) {
					throw new PersistenceException(Titles.NO_PHONE_NUMBERS);
				}
			}

		} catch (SQLException e) {
			throw new PersistenceException(Titles.ERROR_ADDING_PHONE_NUMBERS, e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}

	public boolean addPhoneNumber(Phone phone) throws PersistenceException {
		Connection connection = DBUtils.getConnection();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_PHONE_NUMBER);
			preparedStatement.setString(1, phone.getEmp_id().toString());
			preparedStatement.setString(2, phone.getPhone_number());
			preparedStatement.setString(3, phone.getPhone_type().toString());

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenceException(Titles.NOT_ADDED_PHONE_NUMBER);
			}

		} catch (SQLException e) {
			throw new PersistenceException(Titles.ERROR_ADDING_PHONE_NUMBERS, e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}

	public ArrayList<Phone> findPhoneNumbersOfID(BigInteger emp_id) throws PersistenceException {
		ArrayList<Phone> phoneNumbers = new ArrayList<>();

		Connection connection = DBUtils.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.FIND_PHONE_NUMBERS_OF_ID);
			preparedStatement.setString(1, emp_id.toString());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Phone phone = new Phone();
		
				phone.setEmp_id(emp_id);
				phone.setPhone_id(BigInteger.valueOf(rs.getLong(Fields.PHONE_KEY_ID)));
				phone.setPhone_number(rs.getString(Fields.PHONE_KEY_NUMBER));
				phone.setPhone_type(PhoneType.valueOf(rs.getString(Fields.PHONE_KEY_TYPE)));
				
				phoneNumbers.add(phone);
			}

			return phoneNumbers;
		} catch (SQLException e) {
			throw new PersistenceException(Titles.ERROR_FINDING_PHONE_NUMBERS,e);
			//e.printStackTrace();
		} finally {
			DBUtils.closeConnection(connection);
		}
	}

	public boolean updatePhoneNumberOfID(Phone phone) throws PersistenceException {

		Connection connection = DBUtils.getConnection();

		PreparedStatement updatePhoneNumber;
		try {
			updatePhoneNumber = connection.prepareStatement(Query.UPDATE_PHONE_NUMBER);
			updatePhoneNumber.setString(1, phone.getPhone_number());
			updatePhoneNumber.setString(2, phone.getPhone_type().toString());
			updatePhoneNumber.setString(3, phone.getPhone_id().toString());

			updatePhoneNumber.executeUpdate();
			
			return true;

		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new PersistenceException(Titles.ERROR_UPDATING_PHONE_NUMBERS);
			
		} finally {
			DBUtils.closeConnection(connection);
		}
	}

}
