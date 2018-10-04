package io.ztech.autorate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.constants.SQLConstants;
import io.ztech.autorate.dbutils.DBUtils;

public class RequestCarDAO {

	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet res = null;

	public boolean addCarUserRequest(int carId, String username) throws SQLException {
		System.out.println("carIDDDD " + carId);
		try {

			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_REQUEST);
			pst.setInt(1, carId);
			pst.setString(2, username);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public int getRequestId() throws SQLException {
		int requestId = 0;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_REQUEST_ID);
			res = pst.executeQuery();
			res.next();
			requestId = res.getInt(1);
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return requestId;
	}

	public boolean approveCar(int carId) {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.APPROVE_CAR);
			pst.setInt(1, carId);
			pst.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public ArrayList<Request> getRequests(User user) throws SQLException {
		ArrayList<Request> requests = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			if (user.getAdminStatus().equals("USER")) {
				pst = con.prepareStatement(SQLConstants.SELECT_REQUEST);
				pst.setString(1, user.getUsername());
			} else
				pst = con.prepareStatement(SQLConstants.SELECT_ALL_REQUEST);
			res = pst.executeQuery();
			while (res.next()) {
				Request request = new Request();
				request.setRequestId(res.getInt(AppConstants.REQUEST_ID));
				request.setCarId(res.getInt(AppConstants.CAR_ID));
				request.setUserName(res.getString(AppConstants.USER_NAME));
				requests.add(request);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		if (requests.isEmpty())
			return null;
		else
			return requests;
	}

	public boolean deleteRequest(int requestId) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.DELETE_REQUEST);
			pst.setInt(1, requestId);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

}
