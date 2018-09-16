package io.ztech.autorate.delegate;

import java.sql.SQLException;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.dao.DeleteCarDAO;

public class DeleteCarDelegate {

	DeleteCarDAO deleteCarDAO=new DeleteCarDAO();
	public boolean deleteCar(Specification specification) throws SQLException {
		return deleteCarDAO.deleteCar(specification);
	}
}
