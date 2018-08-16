package io.ztech.carstats.delegate;

import io.ztech.carstats.beans.Rating;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.dao.AddRatingDAO;

public class AddRatingDelegate {
	AddRatingDAO ardao = new AddRatingDAO();

	public boolean addRating(Specification specification, Rating rating, User user) {
		return ardao.addRating(specification, rating, user);
	}
}
