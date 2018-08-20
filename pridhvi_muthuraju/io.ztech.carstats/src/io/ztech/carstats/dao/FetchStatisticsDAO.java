package io.ztech.carstats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.constants.AppConstants;
import io.ztech.carstats.constants.SQLConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class FetchStatisticsDAO {
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet res = null;

	public ArrayList<Statistics> displayAllStatisticsCar(Specification specification) throws Exception {
		ArrayList<Statistics> statistics = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_STATISTICS);
			pst.setInt(1, specification.getCarId());
			res = pst.executeQuery();
			while (res.next()) {
				Statistics statistic = new Statistics();
				statistic.setStatisticsYear(res.getInt(AppConstants.STATISTICS_YEAR));
				statistic.setSaleCount(res.getInt(AppConstants.SALE_COUNT));
				statistics.add(statistic);
			}
		} catch (SQLException e) {
			throw new SQLException(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		return statistics;
	}

	public boolean displayTotalSalesAllCarsYear(Statistics statistics) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_TOTAL_STATISTICS_YEAR);
			pst.setInt(1, statistics.getStatisticsYear());
			res = pst.executeQuery();
			res.next();
			statistics.setSaleCount(res.getInt(AppConstants.SALE_COUNT));
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

	public boolean displayTotalSalesCar(Statistics statistics, Specification specification) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_TOTAL_STATISTICS_CAR);
			pst.setInt(1, specification.getCarId());
			res = pst.executeQuery();
			res.next();
			statistics.setSaleCount(res.getInt(AppConstants.SALE_COUNT));
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return true;
	}

}
