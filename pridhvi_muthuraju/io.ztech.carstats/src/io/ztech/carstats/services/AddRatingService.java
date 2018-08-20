package io.ztech.carstats.services;

import java.sql.SQLException;

import io.ztech.carstats.beans.Rating;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.AddRatingDelegate;

public class AddRatingService {
	AddRatingDelegate addRatingDelegate = new AddRatingDelegate();

	public boolean addRating(Specification specification, Rating rating, User user) throws SQLException {
		return addRatingDelegate.addRating(specification, rating, user);
	}

	public boolean editRating(Specification specification, Rating rating, User user) {
		return addRatingDelegate.editRating(specification, rating, user);
	}
}
