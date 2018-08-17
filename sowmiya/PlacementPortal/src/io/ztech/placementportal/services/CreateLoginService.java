package io.ztech.placementportal.services;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.dao.CreateLoginDao;

public class CreateLoginService {
	Logger log = Logger.getLogger("CreateLogin.class");
	Scanner sc = new Scanner(System.in);

	public boolean generateLogin(Register register) {
		CreateLoginDao createDao = new CreateLoginDao();
		return createDao.createStudentLogin(register);

	}

}
