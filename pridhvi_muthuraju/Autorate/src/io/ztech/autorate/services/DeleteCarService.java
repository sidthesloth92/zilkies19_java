package io.ztech.autorate.services;

import java.sql.SQLException;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.delegate.DeleteCarDelegate;

public class DeleteCarService {

	DeleteCarDelegate deleteCarDelegate =new DeleteCarDelegate();
	public boolean deleteCar(Specification specification) throws SQLException {
		return deleteCarDelegate.deleteCar(specification);
	}
}
