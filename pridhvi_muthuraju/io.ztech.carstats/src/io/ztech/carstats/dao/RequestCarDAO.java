package io.ztech.carstats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.carstats.beans.Request;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.constants.AppConstants;
import io.ztech.carstats.constants.SQLConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class RequestCarDAO {

	private final Logger logger = Logger.getLogger(OutputDAO.class.getName());
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet res = null;;

	public boolean addCarUserRequest(Request request, User user, Specification specification) {

		try {

			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_REQUEST);
			pst.setInt(1, specification.getCarId());
			pst.setString(2, user.getUserName());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
			return false;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public boolean getRequestId(Request request) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_REQUEST_ID);
			res = pst.executeQuery();
			res.next();
			request.setRequestId(res.getInt(1));
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
			return false;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public boolean approveCar(Specification specification) {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.APPROVE_CAR);
			pst.setInt(1, specification.getCarId());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
			return false;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public ArrayList<Request> getRequests() {
		ArrayList<Request> requests = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_ALL_REQUEST);
			res = pst.executeQuery();
			while (res.next()) {
				Request request = new Request();
				request.setRequestId(res.getInt("request_id"));
				request.setCarId(res.getInt("car_id"));
				request.setUserName(res.getString("user_name"));
				requests.add(request);
			}
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
			return null;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return requests;
	}

	public boolean deleteRequest(Request request) {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.DELETE_REQUEST);
			pst.setInt(1, request.getRequestId());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
			return false;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

}
