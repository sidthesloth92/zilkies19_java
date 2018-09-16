package io.ztech.jkingsley.hrmanagement.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.jkingsley.hrmanagement.beans.objects.Project;
import io.ztech.jkingsley.hrmanagement.beans.objects.Skill;
import io.ztech.jkingsley.hrmanagement.constants.Fields;
import io.ztech.jkingsley.hrmanagement.constants.Query;
import io.ztech.jkingsley.hrmanagement.constants.Titles;
import io.ztech.jkingsley.hrmanagement.utils.DBUtils;
import io.ztech.jkingsley.hrmanagement.utils.PersistenceException;

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
				throw new PersistenceException(Titles.NOT_ADDED_PROJECT);
			}

		} catch (SQLException e) {
			throw new PersistenceException(Titles.ERROR_ADDING_PROJECT, e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}

	public ArrayList<Project> findAllProjects() throws PersistenceException {
		ArrayList<Project> projects = new ArrayList<>();

		Connection connection = DBUtils.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.LIST_ALL_PROJECTS);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Project project = new Project();
		
				project.setProject_id(BigInteger.valueOf(rs.getLong(Fields.PROJECT_KEY_ID)));
				project.setProject_name(rs.getString(Fields.PROJECT_KEY_NAME));
				project.setLocation(rs.getString(Fields.PROJECT_KEY_LOCATION));
				project.setReporting_manager_id(BigInteger.valueOf(rs.getLong(Fields.PROJECT_REPORTING_MANAGER_ID)));
				
				projects.add(project);
			}

			return projects;
		} catch (SQLException e) {
			throw new PersistenceException(Titles.ERROR_FINDING_SKILLS,e);
			//e.printStackTrace();
		} finally {
			DBUtils.closeConnection(connection);
		}
	}

}
