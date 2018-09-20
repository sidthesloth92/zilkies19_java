package io.ztech.autorate.delegate;

import java.sql.SQLException;

import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.dao.AddRatingDAO;

public class AddRatingDelegate {
	AddRatingDAO addRatingDAO = new AddRatingDAO();

	public boolean addRating(int carId, Rating rating, String username) throws SQLException {
		return addRatingDAO.addRating(carId, rating, username)
				&& addRatingDAO.isRating(carId, username);
	}

	public boolean editRating(int carId, Rating rating, String username) throws SQLException {
		return addRatingDAO.editRating(carId, rating, username);
	}

	public boolean isRating(int carId, String username) throws SQLException {
		return addRatingDAO.isRating(carId, username);
	}
}
