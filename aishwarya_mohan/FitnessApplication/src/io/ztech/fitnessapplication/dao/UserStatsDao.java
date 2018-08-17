package io.ztech.fitnessapplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserStats;
import io.ztech.fitnessapplication.constants.SQLQueryStringConstants;
import io.ztech.fitnessapplication.dbutils.Config;

public class UserStatsDao {
	private static final Config con_obj = new Config();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public boolean addStats(UserStats newStats) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_STATS);
			ps.setInt(1, newStats.getRegID());
			ps.setString(2, newStats.getGender() + "");
			ps.setInt(3, newStats.getAge());
			ps.setFloat(4, newStats.getHeight());
			ps.setFloat(5, newStats.getWeight());
			ps.setInt(6, newStats.getActivityLevel());
			ps.setFloat(7, newStats.getBmi());
			ps.setInt(8, newStats.getBmr());
			ps.executeUpdate();

		} catch (SQLException e) {
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public UserStats getStats(UserAccount account) {
		UserStats stats = new UserStats();
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_STATS);
			ps.setInt(1, account.getRegID());
			rs = ps.executeQuery();

			if (rs.next()) {
				stats.setGender(rs.getString(2).charAt(0));
				stats.setAge(rs.getInt(3));
				stats.setHeight(rs.getFloat(4));
				stats.setWeight(rs.getFloat(5));
				stats.setActivityLevel(rs.getInt(6));
				stats.setBmi(rs.getFloat(7));
				stats.setBmr(rs.getInt(8));
			}

		} catch (SQLException e) {
			return null;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return stats;
	}

	public boolean setTarget(UserStats newStats) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_TARGET);
			// System.out.println("target"+newStats.getDailyTarget());
			ps.setInt(1, newStats.getDailyTarget());
			ps.setInt(2, newStats.getRegID());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public boolean trackWeight(UserStats stats) {
		try {
			conn = con_obj.getConnection();
			//System.out.println(stats.getRegID());
			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_WEIGHT_LOG);
			ps.setInt(1, stats.getRegID());
			ps.setFloat(2, stats.getWeight());

			ps.executeUpdate();

		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public boolean updateWeight(UserStats stats) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.SET_WEIGHT);
			ps.setFloat(1, stats.getWeight());
			ps.setInt(2, stats.getRegID());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public boolean updateStats(UserStats stats) {
		try {
			conn = con_obj.getConnection();
			ps = conn.prepareStatement(SQLQueryStringConstants.UPDATE_STATS);
			ps.setFloat(1, stats.getHeight());
			ps.setFloat(2, stats.getWeight());
			ps.setInt(3, stats.getAge());
			ps.setString(4, stats.getGender() + "");
			ps.setInt(5, stats.getActivityLevel());
			ps.setFloat(6, stats.getBmi());
			ps.setInt(7, stats.getBmr());
			ps.setInt(8, stats.getDailyTarget());
			ps.setInt(9, stats.getRegID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

}
