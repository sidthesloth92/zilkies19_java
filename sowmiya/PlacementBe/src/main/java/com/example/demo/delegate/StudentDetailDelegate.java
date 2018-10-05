package com.example.demo.delegate;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.example.demo.beans.PersonalInfo;
import com.example.demo.beans.Profile;
import com.example.demo.beans.Register;
import com.example.demo.beans.Student;
import com.example.demo.constants.ApplicationConstants;
import com.example.demo.constants.SqlConstants;
import com.example.demo.dao.AddDetailsDao;
import com.example.demo.dao.CreateLoginDao;
import com.example.demo.dao.DeleteDetailsDao;
import com.example.demo.dao.UpdateStudentDetailDao;


public class StudentDetailDelegate {
	private Logger log;

	public StudentDetailDelegate() {
		log = Logger.getLogger("StudentDetailDelegate.class");
	}

	public boolean addStudentDetail(Student student) {
		log.info("Entering addStudentDetail() ");
		try {
			AddDetailsDao addDao = new AddDetailsDao();
			return addDao.addStudnetDetails(student);
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting addStudentDetail() ");

		return false;
	}

	public Boolean updateStudentDetail(Student student) {
		log.info("Entering updateStudentDetail() ");
		try {
			UpdateStudentDetailDao updateDao = new UpdateStudentDetailDao();
			return updateDao.updateDetail(student);
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting updateStudentDetail() ");

		return false;

	}

	public Boolean deleteStudentDetail(String id) {
		log.info("Entering deleteStudentDetail() in StudentDetailDelegate");
		try {
			DeleteDetailsDao deleteDao = new DeleteDetailsDao();
			return deleteDao.deleteStudent(id);
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting deleteStudentDetail() in StudentDetailDelegate");
		return false;
	}

	public Boolean addProfileDetail(Profile profile, String category) {
		AddDetailsDao addDetailsDao = new AddDetailsDao();
		if (category.equals(ApplicationConstants.ACHIEVEMENT))
			return addDetailsDao.addToProfile(profile, SqlConstants.ADDACHIEVEMENT);
		if (category.equals(ApplicationConstants.PROJECT))
			return addDetailsDao.addToProfile(profile, SqlConstants.ADDPROJECT);
		if (category.equals(ApplicationConstants.CERTIFICATIONS))
			return addDetailsDao.addToProfile(profile, SqlConstants.ADDCERTIFICATION);
		return false;

	}

}
