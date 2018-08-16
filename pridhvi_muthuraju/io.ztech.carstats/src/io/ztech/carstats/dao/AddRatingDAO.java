package io.ztech.carstats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.carstats.beans.Rating;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.constants.AppConstants;
import io.ztech.carstats.constants.SQLConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class AddRatingDAO {

	private final Logger logger = Logger.getLogger(AddRatingDAO.class.getName());
	private Connection con = null;
	private PreparedStatement pst = null;

	public boolean addRating(Specification specification, Rating rating, User user) {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_RATING);
			pst.setInt(1, specification.getCarId());
			pst.setString(2, user.getUserName());
			pst.setString(3, rating.getRating());
			pst.setString(4, rating.getReview());
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
