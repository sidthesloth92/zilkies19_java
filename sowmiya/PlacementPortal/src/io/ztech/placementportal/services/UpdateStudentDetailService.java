package io.ztech.placementportal.services;

import java.sql.SQLException;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.delegate.UpdateStudentDetailDelegate;

public class UpdateStudentDetailService {
	UpdateStudentDetailDelegate updateDelegate = new UpdateStudentDetailDelegate();

	public void updatePlacementDetail(PlacedDetail student) throws SQLException {
		updateDelegate.updatePlacementDetail(student);

	}

	public void updateMarkDetail(Marks mark) throws SQLException {
		updateDelegate.updatePlacementDetail(mark);
	}

	public void updateDetail(Student studentDetail) throws SQLException {
		updateDelegate.updateDetail(studentDetail);
	}

	public void updatePersonalDetail(PersonalInfo info) throws SQLException {
		updateDelegate.updatePersonalDetail(info);
	}

}
