package io.ztech.placementportal.services;

import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.delegate.StudentDetailDelegate;

public class CreateLoginService {
	Logger log = Logger.getLogger("CreateLogin.class");

	public void generateLogin(Register register) throws SQLException {
		StudentDetailDelegate studentDelegate = new StudentDetailDelegate();
		 studentDelegate.createStudentLogin(register);

	}

}
