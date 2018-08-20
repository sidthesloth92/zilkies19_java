package io.ztech.carstats.services;

import java.sql.SQLException;

import io.ztech.carstats.beans.CarType;
import io.ztech.carstats.beans.Make;
import io.ztech.carstats.delegate.CheckMakeCarTypeIDDelegate;

public class CheckMakeCarTypeIDService {

	CheckMakeCarTypeIDDelegate checkMakeCarTypeIDDelegate = new CheckMakeCarTypeIDDelegate();

	public boolean isMakeId(Make make, CarType carType) throws SQLException {
		return checkMakeCarTypeIDDelegate.isMakeId(make, carType);
	}

	public boolean isCarTypeId(Make make, CarType carType) throws SQLException {
		return checkMakeCarTypeIDDelegate.isCarTypeId(make, carType);
	}
}
