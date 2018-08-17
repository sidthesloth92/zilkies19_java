package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class CreateLoginDao {
	Connection connection = DbConnection.getConnection();
	Logger log = Logger.getLogger("CreateLoginDao.class");

	public boolean createStudentLogin(Register register) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		int success;
		try {
			ps = connection.prepareStatement(SqlConstants.CREATE_LOGIN);
			ps.setString(1, register.getUserName());
			ps.setString(2, register.getPassword());
			ps.setString(3, register.getEmail());
			ps.setString(4, register.getRole());
			ps.setInt(5, 1);
			ps.setString(6, register.getReg_no());
			success = ps.executeUpdate();
			if (success >= 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			log.info(e.toString());
		}
		finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return false;
	}

}
