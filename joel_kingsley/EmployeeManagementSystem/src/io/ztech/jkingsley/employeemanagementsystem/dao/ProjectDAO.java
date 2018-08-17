package io.ztech.jkingsley.employeemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.ztech.jkingsley.employeemanagement.constants.Query;
import io.ztech.jkingsley.employeemanagement.utils.DBUtils;
import io.ztech.jkingsley.employeemanagement.utils.PersistenceException;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Project;

public class ProjectDAO {

	public boolean addProject(Project project) throws PersistenceException {
		Connection connection = DBUtils.getConnection();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_PROJECT);
			preparedStatement.setString(1, project.getProject_name());
			preparedStatement.setString(2, project.getLocation());
			preparedStatement.setString(3, project.getReporting_manager_id().toString());

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenceException("Phone number not added");
			}

		} catch (SQLException e) {
			throw new PersistenceException("Error while adding phone number", e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}

}
