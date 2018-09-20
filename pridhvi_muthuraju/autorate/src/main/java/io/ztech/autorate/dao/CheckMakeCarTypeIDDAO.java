package io.ztech.autorate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.autorate.dbutils.DBUtils;

public class CheckMakeCarTypeIDDAO {

	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet res = null;

	public boolean isMakeId(String query) throws SQLException {
		boolean flag = true;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(query);
			res = pst.executeQuery();
			res.next();
			if (res.getInt(1) == 0)
				flag = false;
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);

		}
		return flag;
	}

	public boolean isCarTypeId(String query) throws SQLException {
		boolean flag = true;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(query);
			res = pst.executeQuery();
			res.next();
			if (res.getInt(1) == 0)
				flag = false;
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return flag;
	}
}
