package io.ztech.autorate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.constants.SQLConstants;
import io.ztech.autorate.dbutils.DBUtils;

public class EditCarDAO {
	private Connection con = null;
	private PreparedStatement pst = null;
//	private ResultSet res = null;
	
	public boolean editCar(Specification specification) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.EDIT_CAR);

			pst.setString(1, specification.getCarName());
			pst.setString(2, specification.getEngineType());
			pst.setInt(3, specification.getCylinder());
			pst.setInt(4, specification.getDisplacement());
			pst.setInt(5, specification.getTransmission());
			pst.setInt(6, specification.getPower());
			pst.setInt(7, specification.getTorque());
			pst.setInt(8, specification.getFuelCapacity());
			pst.setInt(9, specification.getWheelbase());
			pst.setInt(10, specification.getKerbWeight());
			pst.setString(11, specification.getAirbag());
			pst.setString(12, specification.getAbs());
			pst.setString(13, specification.getDrivetrain());
			pst.setInt(14, specification.getPrice());
			pst.setInt(15, specification.getCarId());
			pst.executeUpdate();

		} catch (SQLException e) {System.out.println(e);
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}
}
