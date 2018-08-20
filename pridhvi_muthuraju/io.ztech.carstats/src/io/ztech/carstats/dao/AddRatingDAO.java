package io.ztech.carstats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.ztech.carstats.beans.Rating;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.constants.SQLConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class AddRatingDAO {

	private Connection con = null;
	private PreparedStatement pst = null;

	public boolean addRating(Specification specification, Rating rating, User user) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_RATING);
			pst.setInt(1, specification.getCarId());
			pst.setString(2, user.getUserName());
			pst.setString(3, rating.getRating());
			pst.setString(4, rating.getReview());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public boolean isRating(Specification specification, User user) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement("select count(*) from rating where car_id=? and user_name=?");
			pst.setInt(1, specification.getCarId());
			pst.setString(2, user.getUserName());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public boolean editRating(Specification specification, Rating rating, User user) {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.EDIT_RATING);
			pst.setString(1, rating.getRating());
			pst.setString(2, rating.getReview());
			pst.setInt(3, specification.getCarId());
			pst.setString(4, user.getUserName());
			pst.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}
}
