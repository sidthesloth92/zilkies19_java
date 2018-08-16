package io.ztech.carstats.delegate;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.dao.AddCarDAO;
import io.ztech.carstats.dao.DeleteCarDAO;

public class DeleteCarDelegate {

	DeleteCarDAO dcdao=new DeleteCarDAO();
	public boolean deleteCar(Specification specification) {
		return dcdao.deleteCar(specification);
	}
}
