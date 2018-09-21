package io.ztech.placementportal.bean;

import io.ztech.placementportal.constants.ApplicationConstants;

public class Profile {
	private String studentId, title, description;
	private int profileDetailId;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String student_id) {
		this.student_id = student_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProfileDetailId() {
		return profileDetailId;
	}

	public void setProfileDetailId(int profileDetailId) {
		this.profileDetailId = profileDetailId;
	}

	

}
