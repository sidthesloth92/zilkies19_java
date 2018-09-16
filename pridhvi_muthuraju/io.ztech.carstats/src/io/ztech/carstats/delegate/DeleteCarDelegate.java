package io.ztech.carstats.delegate;

import java.sql.SQLException;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.dao.DeleteCarDAO;

public class DeleteCarDelegate {

	DeleteCarDAO deleteCarDAO=new DeleteCarDAO();
	public boolean deleteCar(Specification specification) throws SQLException {
		return deleteCarDAO.deleteCar(specification);
	}
}
