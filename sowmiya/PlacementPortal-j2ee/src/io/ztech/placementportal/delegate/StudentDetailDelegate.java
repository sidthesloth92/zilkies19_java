package io.ztech.placementportal.delegate;

import java.sql.SQLException;

import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.dao.AddDetailsDao;
import io.ztech.placementportal.dao.CreateLoginDao;

public class StudentDetailDelegate {
    AddDetailsDao addDao;
    CreateLoginDao createLoginDao;
    
	public StudentDetailDelegate() {
		addDao=new AddDetailsDao();
		createLoginDao=new CreateLoginDao();
	}
	public boolean addStudentDetail(Student student) throws SQLException {
      return addDao.addStudnetDetails(student);
	}

	public boolean addPersonalDetail(PersonalInfo personalInfo) throws SQLException {
		return addDao.addPersonalInfo(personalInfo);
	}

	public boolean createStudentLogin(Register register) throws SQLException {
		return createLoginDao.createStudentLogin(register);
	}

}
