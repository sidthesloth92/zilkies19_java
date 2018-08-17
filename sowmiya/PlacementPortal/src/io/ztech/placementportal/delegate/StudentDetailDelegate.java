package io.ztech.placementportal.delegate;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.dao.AddDetailsDao;

public class StudentDetailDelegate {
    AddDetailsDao addDao=new AddDetailsDao();
	public boolean addStudentDetail(Student student,Marks mark) {
      return addDao.addStudnetDetails(student, mark);
	}

	public boolean addPersonalDetail(PersonalInfo personalInfo) {
		return addDao.addPersonalInfo(personalInfo);
	}

}
