package io.ztech.placementportal.services;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.delegate.UpdateStudentDetailDelegate;

public class UpdateStudentDetailService {
	UpdateStudentDetailDelegate updateDelegate = new UpdateStudentDetailDelegate();

	public boolean updatePlacementDetail(PlacedDetail student) {
		return updateDelegate.updatePlacementDetail(student);

	}

	public boolean updateMarkDetail(Marks mark) {
		return updateDelegate.updatePlacementDetail(mark);
	}

	public boolean updateDetail(Student studentDetail) {
		return updateDelegate.updateDetail(studentDetail);
	}

	public boolean updatePersonalDetail(PersonalInfo info) {
		return updateDelegate.updatePersonalDetail(info);
	}

}
