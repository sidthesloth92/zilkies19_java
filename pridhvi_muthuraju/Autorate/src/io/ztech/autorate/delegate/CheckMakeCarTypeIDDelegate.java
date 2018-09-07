package io.ztech.autorate.delegate;

import java.sql.SQLException;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.constants.SQLConstants;
import io.ztech.autorate.dao.CheckMakeCarTypeIDDAO;

public class CheckMakeCarTypeIDDelegate {
	CheckMakeCarTypeIDDAO checkMakeCarTypeDAO = new CheckMakeCarTypeIDDAO();

	public boolean isMakeId(Make make, CarType carType) throws SQLException {

		if (carType.getCarTypeId() == 0)
			return checkMakeCarTypeDAO.isMakeId(SQLConstants.IS_MAKE_ID + make.getMakeId());
		else
			return checkMakeCarTypeDAO.isMakeId(SQLConstants.IS_MAKE_ID + make.getMakeId()
					+ SQLConstants.IS_MAKE_ID_CAR_TYPE + carType.getCarTypeId());
	}

	public boolean isCarTypeId(Make make, CarType carType) throws SQLException {
		if (make.getMakeId() == 0)
			return checkMakeCarTypeDAO.isCarTypeId(SQLConstants.IS_CAR_TYPE_ID + carType.getCarTypeId());
		else
			return checkMakeCarTypeDAO.isCarTypeId(SQLConstants.IS_CAR_TYPE_ID + carType.getCarTypeId()
					+ SQLConstants.IS_CAR_TYPE_ID_MAKE + make.getMakeId());
	}
}
