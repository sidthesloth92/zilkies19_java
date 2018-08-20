package io.ztech.carstats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.constants.SQLConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class DeleteCarDAO {

	private Connection con = null;
	private PreparedStatement pst = null;

	public boolean deleteCar(Specification specification) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.DELETE_CAR);
			pst.setInt(1, specification.getCarId());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}
}
