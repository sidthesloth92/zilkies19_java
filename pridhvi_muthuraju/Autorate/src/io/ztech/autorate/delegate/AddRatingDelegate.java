package io.ztech.autorate.delegate;

import java.sql.SQLException;

import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.dao.AddRatingDAO;

public class AddRatingDelegate {
	AddRatingDAO addRatingDAO = new AddRatingDAO();

	public boolean addRating(Specification specification, Rating rating, User user) throws SQLException {
		return addRatingDAO.addRating(specification, rating, user) && addRatingDAO.isRating(specification, user);
	}

	public boolean editRating(Specification specification, Rating rating, User user) {
		return addRatingDAO.editRating(specification, rating, user);
	}
}
