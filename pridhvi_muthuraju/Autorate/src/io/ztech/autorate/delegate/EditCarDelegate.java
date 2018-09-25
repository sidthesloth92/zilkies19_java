package io.ztech.autorate.delegate;

import java.sql.SQLException;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.dao.EditCarDAO;

public class EditCarDelegate {
	EditCarDAO editCarDAO = new EditCarDAO();
	
	public boolean editCar(Specification specification) throws SQLException {
		return editCarDAO.editCar(specification);
	}
}
