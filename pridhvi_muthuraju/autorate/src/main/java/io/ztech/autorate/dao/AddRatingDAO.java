package io.ztech.autorate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.constants.SQLConstants;
import io.ztech.autorate.dbutils.DBUtils;

public class AddRatingDAO {

	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet res = null;

	public boolean addRating(int carId, Rating rating, String username) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_RATING);
			pst.setInt(1, carId);
			pst.setString(2, username);
			pst.setString(3, rating.getRating());
			pst.setString(4, rating.getReview());
			pst.setString(5, rating.getSubject());
			pst.executeUpdate();
		} catch (SQLException e) {System.out.println(e);
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public boolean isRating(int carId, String username) throws SQLException {
		Boolean flag = true;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement("select count(*) from rating where car_id=? and user_name=?");
			pst.setInt(1, carId);
			pst.setString(2, username);
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

	public boolean editRating(int carId, Rating rating, String username) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.EDIT_RATING);
			pst.setString(1, rating.getRating());
			pst.setString(2, rating.getReview());
			pst.setString(3, rating.getSubject());
			pst.setInt(4, carId);
			pst.setString(5, username);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

}
