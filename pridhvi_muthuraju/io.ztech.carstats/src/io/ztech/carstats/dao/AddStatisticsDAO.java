package io.ztech.carstats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.constants.AppConstants;
import io.ztech.carstats.constants.SQLConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class AddStatisticsDAO {
	private final Logger logger = Logger.getLogger(FetchDetailsDAO.class.getName());
	private Connection con = null;
	private PreparedStatement pst = null;

	public boolean addStatistics(Specification specification, Statistics statistics) {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_STATISTICS);
			pst.setInt(1, specification.getCarId());
			pst.setInt(2, statistics.getStatisticsYear());
			pst.setInt(3, statistics.getSaleCount());
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
