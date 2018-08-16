package io.ztech.carstats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import io.ztech.carstats.beans.CarType;
import io.ztech.carstats.beans.Make;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.constants.AppConstants;
import io.ztech.carstats.constants.SQLConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class AddCarDAO {
	private final Logger logger = Logger.getLogger(OutputDAO.class.getName());
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet res = null;

	public boolean addCar(CarType carType, Make make, Specification specification)
			throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_CAR);

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
			pst.setString(15, specification.getCarStatus());
			pst.executeUpdate();

		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
			return false;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return false;
	}

	public boolean addCarId(CarType carType, Make make, Specification specification) {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_CAR_ID);
			pst.setInt(1, specification.getCarId());
			pst.setInt(2, make.getMakeId());
			pst.setInt(3, carType.getCarTypeId());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
			return false;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public int getMakeId(Make make) throws SQLException {
		if (make.getMakeId() == 0) {
			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.INSERT_MAKE);
				pst.setString(1, make.getMakeName());
				pst.executeUpdate();
			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
				e.printStackTrace();
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}
			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.SELECT_MAKE_ID);
				pst.setString(1, make.getMakeName());
				res = pst.executeQuery();
				res.next();
				make.setMakeId(res.getInt(AppConstants.MAKE_ID));
			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
				e.printStackTrace();
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}
		}
		return make.getMakeId();
	}

	public int getCarTypeId(CarType carType) throws SQLException {
		if (carType.getCarTypeId() == 0) {
			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.INSERT_CAR_TYPE);
				pst.setString(1, carType.getCarTypeName());
				pst.executeUpdate();
			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
				e.printStackTrace();
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}
			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.SELECT_CAR_TYPE_ID);
				pst.setString(1, carType.getCarTypeName());
				res = pst.executeQuery();
				res.next();
				carType.setCarTypeId(res.getInt(AppConstants.CAR_TYPE_ID));
			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
				e.printStackTrace();
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}
		}
		return carType.getCarTypeId();
	}

	public int getCarId() throws SQLException {
		int carId = 0;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_CAR_ID);
			res = pst.executeQuery();
			res.next();
			carId = res.getInt(1);
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return carId;
	}

}
