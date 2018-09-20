package io.ztech.autorate.delegate;

import java.sql.SQLException;

import io.ztech.autorate.dao.DeleteCarDAO;

public class DeleteCarDelegate {

	DeleteCarDAO deleteCarDAO = new DeleteCarDAO();

	public boolean deleteCar(int carId) throws SQLException {
		return deleteCarDAO.deleteCar(carId);
	}
}
