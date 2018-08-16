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

public class OutputDAO {
	private final Logger logger = Logger.getLogger(OutputDAO.class.getName());
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
			DBUtils.closeConnection(con, pst, null);
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
			DBUtils.closeConnection(con, pst, null);
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
			pst = con.prepareStatement("select user_name,rating,review from rating where car_id=?");
			pst.setInt(1, specification.getCarId());
			res = pst.executeQuery();
			while (res.next()) {
				Rating rating = new Rating();
				rating.setRating(res.getString("rating"));
				rating.setReview(res.getString("review"));
				ratings.put(res.getString("user_name"), rating);
			}
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return ratings;
	}

	public ArrayList<Statistics> displayStatistics(Specification specification) {
		ArrayList<Statistics> statistics = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement("select statistics_year,sale_count from statistics where car_id=?");
			pst.setInt(1, specification.getCarId());
			res = pst.executeQuery();
			while (res.next()) {
				Statistics statistic = new Statistics();
				statistic.setStatisticsYear(res.getInt("statistics_year"));
				statistic.setSaleCount(res.getInt("sale_count"));
				statistics.add(statistic);
			}
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, null);
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
			DBUtils.closeConnection(con, pst, null);
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
				specification.setCarId(res.getInt("car_id"));
				specification.setAbs(res.getString("abs"));
				specification.setAirbag(res.getString("airbag"));
				specification.setCarName(res.getString("car_name"));
				specification.setCarStatus(res.getString("car_status"));
				specification.setCylinder(res.getInt("cylinder"));
				specification.setDisplacement(res.getInt("displacement"));
				specification.setDrivetrain(res.getString("drivetrain"));
				specification.setEngineType(res.getString("engine_type"));
				specification.setFuelCapacity(res.getInt("fuel_capacity"));
				specification.setKerbWeight(res.getInt("kerb_weight"));
				specification.setPower(res.getInt("power"));
				specification.setPrice(res.getInt("price"));
				specification.setTorque(res.getInt("torque"));
				specification.setTransmission(res.getInt("transmission"));
				specification.setWheelbase(res.getInt("wheelbase"));
			}
		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return specification;
	}

}
