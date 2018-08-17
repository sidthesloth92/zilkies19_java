package io.ztech.placementportal.delegate;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.dao.UpdateStudentDetailDao;

public class UpdateStudentDetailDelegate {
	UpdateStudentDetailDao updateDao = new UpdateStudentDetailDao();

	public boolean updatePlacementDetail(PlacedDetail student) {
		return updateDao.updatePlacementDetail(student);
	}

	public boolean updatePlacementDetail(Marks mark) {
		return updateDao.updateMarkDetail(mark);
	}

	public boolean updateDetail(Student studentDetail) {
		return updateDao.updateMarkDetail(studentDetail);
	}

	public boolean updatePersonalDetail(PersonalInfo info) {
		return updateDao.updatePersonalDetail(info);
	}


}
