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
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Experience;

public class ExperienceDAO {
	public boolean addExperiences(ArrayList<Experience> experiences) throws PersistenceException {

		Connection connection = DBUtils.getConnection();

		try {

			for (Experience experience : experiences) {
				PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_EXPERIENCE);
				preparedStatement.setString(1, experience.getEmp_id().toString());
				preparedStatement.setString(2, experience.getSkill_id().toString());

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows == 0) {
					throw new PersistenceException("No experiences added");
				}
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new PersistenceException("Error while adding experiences", e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}

	public boolean addExperience(Experience experience) throws PersistenceException {
		Connection connection = DBUtils.getConnection();

		try {

				PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_EXPERIENCE);
				preparedStatement.setString(1, experience.getEmp_id().toString());
				preparedStatement.setString(2, experience.getSkill_id().toString());

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows == 0) {
					throw new PersistenceException("No experience added");
				}

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new PersistenceException("Error while adding experience", e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}


	public ArrayList<Experience> findTotalExperienceOfID(BigInteger emp_id) throws PersistenceException {
		ArrayList<Experience> totalExperience = new ArrayList<>();

		Connection connection = DBUtils.getConnection();
		ResultSet rs = null;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.FIND_TOTAL_EXPERIENCE_OF_ID);
			preparedStatement.setString(1, emp_id.toString());

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Experience experience = new Experience();
		
				experience.setEmp_id(emp_id);
				experience.setSkill_id(BigInteger.valueOf(rs.getLong(Fields.EXPERIENCE_KEY_SKILL_ID)));
				
				totalExperience.add(experience);
			}

			return totalExperience;
		} catch (SQLException e) {
			throw new PersistenceException("Error in finding all experiences",e);
			//e.printStackTrace();
		} finally {
			DBUtils.closeConnection(connection,rs);
		}
	}
	
}
