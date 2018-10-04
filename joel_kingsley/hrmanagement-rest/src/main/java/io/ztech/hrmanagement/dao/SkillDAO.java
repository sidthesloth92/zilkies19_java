package io.ztech.hrmanagement.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.hrmanagement.beans.objects.Skill;
import io.ztech.hrmanagement.constants.Fields;
import io.ztech.hrmanagement.constants.Query;
import io.ztech.hrmanagement.constants.Titles;
import io.ztech.hrmanagement.utils.DBUtils;
import io.ztech.hrmanagement.utils.PersistenceException;

public class SkillDAO {
	public ArrayList<Skill> findAllSkills() throws PersistenceException {
		ArrayList<Skill> skills = new ArrayList<>();

		Connection connection = DBUtils.getConnection();

		try {
			PreparedStatement listSkills = connection.prepareStatement(Query.LIST_ALL_SKILLS);

			ResultSet rs = listSkills.executeQuery();

			while (rs.next()) {
				Skill skill = new Skill();
		
				skill.setSkill_id(BigInteger.valueOf(rs.getLong(Fields.SKILL_KEY_ID)));
				skill.setSkill_name(rs.getString(Fields.SKILL_KEY_NAME));
				
				skills.add(skill);
			}

			return skills;
		} catch (SQLException e) {
			throw new PersistenceException(Titles.ERROR_FINDING_SKILLS,e);
			//e.printStackTrace();
		} finally {
			DBUtils.closeConnection(connection);
		}
	}
	

	public boolean addSkill(Skill skill) throws PersistenceException {
		Connection connection = DBUtils.getConnection();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(Query.ADD_SKILL);
			preparedStatement.setString(1, skill.getSkill_name());

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenceException(Titles.NO_SKILL_ADDED);
			}

		} catch (SQLException e) {
			throw new PersistenceException(Titles.ERROR_ADDING_SKILL, e);
		} finally {
			DBUtils.closeConnection(connection);
		}

		return true;
	}
}
