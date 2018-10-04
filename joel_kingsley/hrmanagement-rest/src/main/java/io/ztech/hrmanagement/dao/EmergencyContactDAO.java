package io.ztech.hrmanagement.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.hrmanagement.beans.objects.EmergencyContact;
import io.ztech.hrmanagement.beans.objects.Skill;
import io.ztech.hrmanagement.constants.Fields;
import io.ztech.hrmanagement.constants.Query;
import io.ztech.hrmanagement.utils.DBUtils;
import io.ztech.hrmanagement.utils.PersistenceException;

public class EmergencyContactDAO {
	public boolean addEmergencyContacts(ArrayList<EmergencyContact> emergenctContacts) throws PersistenceException {

		Connection connection = DBUtils.getConnection();

		try {

			for (EmergencyContact emergencyContact : emergenctContacts) {
				PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_EMERGENCY_CONTACT);
				preparedStatement.setString(1, emergencyContact.getEmp_id().toString());
				preparedStatement.setString(2, emergencyContact.getEmergency_contact_name());
				preparedStatement.setString(3, emergencyContact.getEmergency_contact_phone());

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows == 0) {
					throw new PersistenceException("No emergency Contacts added");
				}
			}

		} catch (SQLException e) {
			throw new PersistenceException("Error while adding emergency contacts", e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}

	public boolean addEmergencyContact(EmergencyContact emergencyContact) throws PersistenceException {

		Connection connection = DBUtils.getConnection();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_EMERGENCY_CONTACT);
			preparedStatement.setString(1, emergencyContact.getEmp_id().toString());
			preparedStatement.setString(2, emergencyContact.getEmergency_contact_name());
			preparedStatement.setString(3, emergencyContact.getEmergency_contact_phone());

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenceException("Emergency Contact not added");
			}

		} catch (SQLException e) {
			throw new PersistenceException("Error while adding emergency contact", e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}

	public ArrayList<EmergencyContact> findEmergencyContactsOfID(BigInteger emp_id) throws PersistenceException {
		ArrayList<EmergencyContact> emergencyContacts = new ArrayList<>();

		Connection connection = DBUtils.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.FIND_EMERGENCY_CONTACTS_OF_ID);
			preparedStatement.setString(1, emp_id.toString());
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				EmergencyContact emergencyContact = new EmergencyContact();
		
				emergencyContact.setEmp_id(emp_id);
				emergencyContact.setEmergency_contact_id(BigInteger.valueOf(rs.getLong(Fields.EMERGENCY_CONTACT_KEY_ID)));
				emergencyContact.setEmergency_contact_name(rs.getString(Fields.EMERGENCY_CONTACT_KEY_NAME));
				emergencyContact.setEmergency_contact_phone(rs.getString(Fields.EMERGENCY_CONTACT_KEY_PHONE));
				
				emergencyContacts.add(emergencyContact);
			}

			return emergencyContacts;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Error in finding all emergency contacts",e);
		}finally {
			DBUtils.closeConnection(connection);
		}
	}

	public boolean updateEmergencyContactOfID(EmergencyContact emergencyContact2) throws PersistenceException {
		Connection connection = DBUtils.getConnection();

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(Query.UPDATE_EMERGENCY_CONTACT);
			preparedStatement.setString(1, emergencyContact2.getEmergency_contact_name());
			preparedStatement.setString(2, emergencyContact2.getEmergency_contact_phone());
			preparedStatement.setString(3, emergencyContact2.getEmergency_contact_id().toString());

			preparedStatement.executeUpdate();
			
			return true;

		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new PersistenceException("Error while updating emergency contact number");
			
		} finally {
			DBUtils.closeConnection(connection);
		}
	}
	
	

}
