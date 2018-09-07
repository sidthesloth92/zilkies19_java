package io.ztech.autorate.services;

import java.sql.SQLException;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.delegate.CheckMakeCarTypeIDDelegate;

public class CheckMakeCarTypeIDService {

	CheckMakeCarTypeIDDelegate checkMakeCarTypeIDDelegate = new CheckMakeCarTypeIDDelegate();

	public boolean isMakeId(Make make, CarType carType) throws SQLException {
		return checkMakeCarTypeIDDelegate.isMakeId(make, carType);
	}

	public boolean isCarTypeId(Make make, CarType carType) throws SQLException {
		return checkMakeCarTypeIDDelegate.isCarTypeId(make, carType);
	}
}
