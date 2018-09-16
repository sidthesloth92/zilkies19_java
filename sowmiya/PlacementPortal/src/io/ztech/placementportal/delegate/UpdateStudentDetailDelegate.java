package io.ztech.placementportal.delegate;

import java.sql.SQLException;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.dao.UpdateStudentDetailDao;

public class UpdateStudentDetailDelegate {
	UpdateStudentDetailDao updateDao = new UpdateStudentDetailDao();

	public void updatePlacementDetail(PlacedDetail student) throws SQLException {
		updateDao.updatePlacementDetail(student);
	}

	public void updatePlacementDetail(Marks mark) throws SQLException {
		updateDao.updateMarkDetail(mark);
	}

	public void updateDetail(Student studentDetail) throws SQLException {
		updateDao.updateMarkDetail(studentDetail);
	}

	public void updatePersonalDetail(PersonalInfo info) throws SQLException {
		updateDao.updatePersonalDetail(info);
	}

}
