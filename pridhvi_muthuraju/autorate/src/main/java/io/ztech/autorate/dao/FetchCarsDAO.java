package io.ztech.autorate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.constants.SQLConstants;
import io.ztech.autorate.dbutils.DBUtils;

public class FetchCarsDAO {
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet res = null;

	public ArrayList<Make> displayMakes(String query) throws SQLException {
		ArrayList<Make> makes = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(query);
			res = pst.executeQuery();
			while (res.next()) {
				Make make = new Make();
				make.setMakeId(res.getInt(AppConstants.MAKE_ID));
				make.setMakeName(res.getString(AppConstants.MAKE_NAME));
				makes.add(make);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		if (makes.isEmpty())
			return null;
		else
			return makes;
	}

	public ArrayList<CarType> displayCarTypes(String query) throws SQLException {
		ArrayList<CarType> carTypes = new ArrayList<CarType>();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(query);
			res = pst.executeQuery();

			while (res.next()) {
				CarType carType = new CarType();
				carType.setCarTypeId(res.getInt(AppConstants.CAR_TYPE_ID));
				carType.setCarTypeName(res.getString(AppConstants.CAR_TYPE_NAME));
				carTypes.add(carType);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		if (carTypes.isEmpty())
			return null;
		else
			return carTypes;
	}

	public HashMap<String, Rating> displayRating(Specification specification) throws SQLException {

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
				rating.setSubject(res.getString(AppConstants.SUBJECT));
				ratings.put(res.getString(AppConstants.USER_NAME), rating);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		if (ratings.isEmpty())
			return null;
		else
			return ratings;
	}

	public Rating getRating(int carId, String username) throws SQLException {

		Rating rating = new Rating();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_RATING_USER);
			pst.setInt(1, carId);
			pst.setString(2, username);
			res = pst.executeQuery();
			res.next();
			rating.setRating(res.getString(AppConstants.RATING));
			rating.setReview(res.getString(AppConstants.REVIEW));
			rating.setSubject(res.getString(AppConstants.SUBJECT));
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
		return rating;
	}

	public ArrayList<Specification> getCars(int makeId, int carTypeId) throws SQLException {

		ArrayList<Specification> cars = new ArrayList<Specification>();
		try {
			con = DBUtils.getConnection();
			if (makeId == 0) {
				pst = con.prepareStatement(SQLConstants.SELECT_ALL_CARS_TYPE);
				pst.setInt(1, carTypeId);
			} else if (makeId == 0 && carTypeId == 0) {
				pst = con.prepareStatement(SQLConstants.ALL_CARS);
			} else {
				pst = con.prepareStatement(SQLConstants.SELECT_ALL_CAR);
				pst.setInt(1, makeId);
				pst.setInt(2, carTypeId);
			}
			res = pst.executeQuery();

			while (res.next()) {
				Specification specification = new Specification();
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
				cars.add(specification);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		return cars;
	}

	public Specification getCar(Request request) throws SQLException {
		Specification specification = new Specification();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_CAR);
			pst.setInt(1, request.getCarId());
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
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		return specification;
	}

	public Specification getCarByRequest(Request request) throws SQLException {
		Specification specification = new Specification();
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_CAR_BY_REQUEST);
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
			throw new SQLException();
		} finally {
			DBUtils.closeConnection(con, pst, res);
		}
		return specification;
	}

}
