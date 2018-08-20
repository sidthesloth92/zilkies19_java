package io.ztech.carstats.delegate;

import java.sql.SQLException;

import io.ztech.carstats.beans.Rating;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.dao.AddRatingDAO;

public class AddRatingDelegate {
	AddRatingDAO addRatingDAO = new AddRatingDAO();

	public boolean addRating(Specification specification, Rating rating, User user) throws SQLException {
		return addRatingDAO.addRating(specification, rating, user) && addRatingDAO.isRating(specification, user);
	}

	public boolean editRating(Specification specification, Rating rating, User user) {
		return addRatingDAO.editRating(specification, rating, user);
	}
}
