package io.ztech.placementportal.delegate;

import java.sql.SQLException;

import io.ztech.placementportal.bean.Marks;
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
	public void addStudentDetail(Student student,Marks mark) throws SQLException {
      addDao.addStudnetDetails(student, mark);
	}

	public void addPersonalDetail(PersonalInfo personalInfo) throws SQLException {
		 addDao.addPersonalInfo(personalInfo);
	}

	public void createStudentLogin(Register register) throws SQLException {
		createLoginDao.createStudentLogin(register);
	}

}
