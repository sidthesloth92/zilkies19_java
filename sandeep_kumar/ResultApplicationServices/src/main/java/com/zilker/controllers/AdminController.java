package com.zilker.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.beans.CollegeData;
import com.zilker.beans.FacultyData;
import com.zilker.beans.ResultData;
import com.zilker.beans.StudentData;
import com.zilker.beans.SubjectData;
import com.zilker.delegates.AdminDelegate;

@RestController
@RequestMapping("/ResultApplication/admin")
public class AdminController {

	@GetMapping("/students")
	public ArrayList<StudentData> getAllStudentDetails() {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.getAllStudentDetails();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/students")
	public boolean addStudentDetails(@RequestBody StudentData student) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.addStudentDetails(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@PostMapping("/registerStudents")
	public boolean registerStudentDetails(@RequestBody StudentData student) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.registerStudent(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@PostMapping("/registerFaculty")
	public boolean registeFacultyDetails(@RequestBody FacultyData faculty) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.registerFaculty(faculty);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@PutMapping("/students/{studentRegistrationNumber}")
	public boolean updateStudentDetails(@PathVariable("studentRegistrationNumber") long studentRegistrationNumber,
			@RequestBody StudentData student) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.updateStudentDetails(studentRegistrationNumber, student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@DeleteMapping("/students/{studentRegistrationNumber}")
	public boolean deleteStudentDetails(@PathVariable("studentRegistrationNumber") long studentRegistrationNumber) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.deleteStudentDetails(studentRegistrationNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@GetMapping("/faculty")
	public ArrayList<FacultyData> getAllFacultyDetails() {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.getAllFacultyDetails();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/faculty")
	public boolean addfacultyDetails(@RequestBody FacultyData faculty) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.addFacultyDetails(faculty);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@PutMapping("/faculty/{facultyRegistrationNumber}")
	public boolean updatefacultyDetails(@PathVariable("facultyRegistrationNumber") long facultyRegistrationNumber,
			@RequestBody FacultyData faculty) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.updateFacultyDetails(facultyRegistrationNumber, faculty);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@DeleteMapping("/faculty/{facultyRegistrationNumber}")
	public boolean deletefacultyDetails(@PathVariable("facultyRegistrationNumber") long facultyRegistrationNumber) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.deleteFacultyDetails(facultyRegistrationNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@GetMapping("/colleges")
	public ArrayList<CollegeData> getAllCollegeDetails() {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.getAllCollegeDetails();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/colleges")
	public boolean addCollegeDetails(@RequestBody CollegeData college) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.addCollegeDetails(college);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@PutMapping("/colleges/{collegeCode}")
	public boolean updateCollegeDetails(@PathVariable("collegeCode") int collegeCode,
			@RequestBody CollegeData college) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.updateCollegeDetails(collegeCode, college);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@DeleteMapping("/colleges/{collegeCode}")
	public boolean deleteCollegeDetails(@PathVariable("collegeCode") int collegeCode) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.deleteCollegeDetails(collegeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@GetMapping("/subjects")
	public ArrayList<SubjectData> getAllSubjectDetails() {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.getAllSubjectDetails();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/subjects")
	public boolean addSubjectDetails(@RequestBody SubjectData subject) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.addSubjectDetails(subject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@PutMapping("/subjects/{subjectCode}")
	public boolean updateSubjectDetails(@PathVariable("subjectCode") String subjectCode,
			@RequestBody SubjectData subject) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.updateSubjectDetails(subjectCode, subject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@DeleteMapping("/subjects/{subjectCode}")
	public boolean deleteSubjectDetails(@PathVariable("subjectCode") String subjectCode) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.deleteSubjectDetails(subjectCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@GetMapping("/results")
	public ArrayList<ResultData> getAllResultDetails() {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.getAllResultDetails();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/lastStudentRegistrationNumber/{collegeCode}")
	public long getLastStudentRegistrationNumber(@PathVariable("collegeCode") int collegeCode) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.getLastStudentRegistrationNumber(collegeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@GetMapping("/lastFacultyFacultyRegistrationNumber/{collegeCode}")
	public long getLastRegistrationNumber(@PathVariable("collegeCode") int collegeCode) {
		AdminDelegate admin = new AdminDelegate();
		try {
			return admin.getLastFacultyRegistrationNumber(collegeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
