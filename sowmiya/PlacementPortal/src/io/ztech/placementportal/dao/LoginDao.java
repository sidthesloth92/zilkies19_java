package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class LoginDao {

	public String login(Register login) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String reg_no = "";
			ps = connection.prepareStatement(SqlConstants.LOGIN_SQL);
			ps.setString(1, login.getUserName());
			ps.setString(2, login.getPassword());
			ps.setString(3, login.getRole());
			ps.setInt(4, 1);
			rs = ps.executeQuery();
			while (rs.next()) {
				reg_no = rs.getString("reg_no");
			}
			return reg_no;

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;

	}

}
