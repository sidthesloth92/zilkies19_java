package io.ztech.placementportal.delegate;

import java.sql.SQLException;

import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.dao.UpdateStudentDetailDao;

public class UpdateStudentDetailDelegate {
	UpdateStudentDetailDao updateDao = new UpdateStudentDetailDao();

	public void updatePlacementDetail(PlacedDetail student) throws SQLException {
		updateDao.updatePlacementDetail(student);
	}


	public boolean updateDetail(Student studentDetail) throws SQLException {
		return updateDao.updateDetail(studentDetail);
	
	}

	public void updatePersonalDetail(PersonalInfo info) throws SQLException {
		updateDao.updatePersonalDetail(info);
	}

}
