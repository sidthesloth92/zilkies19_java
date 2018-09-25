package io.ztech.autorate.services;

import java.sql.SQLException;

import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.delegate.AddRatingDelegate;

public class AddRatingService {
	AddRatingDelegate addRatingDelegate = new AddRatingDelegate();

	public boolean addRating(Specification specification, Rating rating, User user) throws SQLException {
		return addRatingDelegate.addRating(specification, rating, user);
	}

	public boolean editRating(Specification specification, Rating rating, User user) {
		return addRatingDelegate.editRating(specification, rating, user);
	}
	
	public boolean isRating(Specification specification,User user) throws SQLException {
		return addRatingDelegate.isRating(specification, user);
	}
}
