package io.ztech.carstats.dao;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.carstats.dbutils.*;
import io.ztech.carstats.beans.*;
import io.ztech.carstats.constants.*;

public class FetchDetailsDAO {
	private final Logger logger = Logger.getLogger(FetchDetailsDAO.class.getName());
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet res = null;

	public HashMap<Integer, String> displayMakes() throws SQLException {
		HashMap<Integer, String> makes = new HashMap<>();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_ALL_MAKE);
			res = pst.executeQuery();
			while (res.next()) {
				makes.put(res.getInt(AppConstants.MAKE_ID), res.getString(AppConstants.MAKE_NAME));
			}
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		if (makes.isEmpty())
			return null;
		else
			return makes;
	}

	public HashMap<Integer, String> displayCarTypes() throws SQLException {
		HashMap<Integer, String> carTypes = new HashMap<>();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_ALL_CAR_TYPE);
			res = pst.executeQuery();

			while (res.next()) {
				carTypes.put(res.getInt(AppConstants.CAR_TYPE_ID), res.getString(AppConstants.CAR_TYPE_NAME));
			}
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		if (carTypes.isEmpty())
			return null;
		else
			return carTypes;
	}

	public HashMap<String, Rating> displayRating(Specification specification) {

		HashMap<String, Rating> ratings = new HashMap<String, Rating>();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_RATING);
			pst.setInt(1, specification.getCarId());
			res = pst.executeQuery();
			while (res.next()) {
				Rating rating = new Rating();
				rating.setRating(res.getString(AppConstants.RATING));
				rating.setReview(res.getString(AppConstants.REVIEW));
				ratings.put(res.getString(AppConstants.USER_NAME), rating);
			}
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		if (ratings.isEmpty())
			return null;
		else
			return ratings;
	}

	public ArrayList<Statistics> displayStatistics(Specification specification) {
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
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		return statistics;
	}

	public HashMap<Integer, ArrayList<String>> getCars(Make make, CarType carType) throws SQLException {
		if (make.getMakeId() == 0 || carType.getCarTypeId() == 0) {
			return null;
		}
		HashMap<Integer, ArrayList<String>> cars = new HashMap<>();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_ALL_CAR);
			pst.setInt(1, make.getMakeId());
			pst.setInt(2, carType.getCarTypeId());
			res = pst.executeQuery();

			while (res.next()) {
				ArrayList<String> specs = new ArrayList<>();
				specs.add(res.getString(2));
				specs.add(res.getString(3));
				specs.add(Integer.toString(res.getInt(4)));
				specs.add(Integer.toString(res.getInt(5)));
				specs.add(Integer.toString(res.getInt(6)));
				specs.add(Integer.toString(res.getInt(7)));
				specs.add(Integer.toString(res.getInt(8)));
				specs.add(Integer.toString(res.getInt(9)));
				specs.add(Integer.toString(res.getInt(10)));
				specs.add(Integer.toString(res.getInt(11)));
				specs.add(res.getString(12));
				specs.add(res.getString(13));
				specs.add(res.getString(14));
				specs.add(Integer.toString(res.getInt(15)));
				specs.add(res.getString(16));
				cars.put(res.getInt(1), specs);
			}
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		return cars;
	}

	public Specification getCar(Request request) {
		Specification specification = new Specification();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_CAR);
			pst.setInt(1, request.getRequestId());
			res = pst.executeQuery();

			while (res.next()) {
				specification.setCarId(res.getInt(AppConstants.CAR_ID));
				specification.setAbs(res.getString(AppConstants.ABS));
				specification.setAirbag(res.getString(AppConstants.AIRBAG));
				specification.setCarName(res.getString(AppConstants.CAR_NAME));
				specification.setCarStatus(res.getString(AppConstants.CAR_STATUS));
				specification.setCylinder(res.getInt(AppConstants.CYLINDER));
				specification.setDisplacement(res.getInt(AppConstants.DISPLACEMENT));
				specification.setDrivetrain(res.getString(AppConstants.DRIVETRAIN));
				specification.setEngineType(res.getString(AppConstants.ENGINE_TYPE));
				specification.setFuelCapacity(res.getInt(AppConstants.FUEL_CAPACITY));
				specification.setKerbWeight(res.getInt(AppConstants.KERB_WEIGHT));
				specification.setPower(res.getInt(AppConstants.POWER));
				specification.setPrice(res.getInt(AppConstants.PRICE));
				specification.setTorque(res.getInt(AppConstants.TORQUE));
				specification.setTransmission(res.getInt(AppConstants.TRANSMISSION));
				specification.setWheelbase(res.getInt(AppConstants.WHEELBASE));
			}
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		return specification;
	}

}
