package io.ztech.autorate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.SQLConstants;
import io.ztech.autorate.dbutils.DBUtils;

public class AddRatingDAO {

	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet res = null;

	public boolean addRating(Specification specification, Rating rating, User user) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_RATING);
			pst.setInt(1, specification.getCarId());
			pst.setString(2, user.getUsername());
			pst.setString(3, rating.getRating());
			pst.setString(4, rating.getReview());
			pst.setString(5, rating.getSubject());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public boolean isRating(Specification specification, User user) throws SQLException {
		Boolean flag=true;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement("select count(*) from rating where car_id=? and user_name=?");
			pst.setInt(1, specification.getCarId());
			pst.setString(2, user.getUsername());
			res=pst.executeQuery();
			res.next();
			if(res.getInt(1)==0)flag=false;
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return flag;
	}

	public boolean editRating(Specification specification, Rating rating, User user) {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.EDIT_RATING);
			pst.setString(1, rating.getRating());
			pst.setString(2, rating.getReview());
			pst.setString(3, rating.getSubject());
			pst.setInt(4, specification.getCarId());
			pst.setString(5, user.getUsername());
			pst.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}
	
}
