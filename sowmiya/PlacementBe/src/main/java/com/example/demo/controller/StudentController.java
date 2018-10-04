package com.example.demo.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.support.GenericTypeAwareAutowireCandidateResolver;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Profile;
import com.example.demo.beans.Student;
import com.example.demo.constants.ApplicationConstants;
import com.example.demo.delegate.RetrieveDetailDelegate;
import com.example.demo.delegate.StudentDetailDelegate;

@RestController
@RequestMapping("/demo")
public class StudentController {
	private Logger log;

	public StudentController() {
		log = Logger.getLogger("StudentController.class");
	}

	@GetMapping("/students")
	public ArrayList<Student> getStudentsDetail() {
		log.info("Entering getStudentDetail() of student controller");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		return retrieveDelegate.getStudentDetails();
	}

	@GetMapping("/students/{id}")
	public Student getSpecificStudentDetail(@PathVariable String id) {
		log.info("Entering getSpecificStudentDetail() of student controller");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		return retrieveDelegate.getSpecificStudentDetail(id);
	}

	@GetMapping("/students/achievements/{id}")
	public ArrayList<Profile> getAchievementDetails(@PathVariable String id) {
		log.info("Entering getAchievementDetails() of student controller");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		return retrieveDelegate.getProfileDetail(id, ApplicationConstants.ACHIEVEMENT);
	}

	@GetMapping("/students/projects/{id}")
	public ArrayList<Profile> getProjectDetails(@PathVariable String id) {
		log.info("Entering getProjectDetails() of student controller");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		return retrieveDelegate.getProfileDetail(id, ApplicationConstants.PROJECT);
	}

	@GetMapping("/students/courses/{id}")
	public ArrayList<Profile> getCourseDetails(@PathVariable String id) {
		log.info("Entering getCourseDetails() of student controller");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		return retrieveDelegate.getProfileDetail(id, ApplicationConstants.CERTIFICATIONS);
	}

	@PostMapping("/students")
	public Boolean addStudentDetail(@RequestBody Student student) {
		log.info("Entering addStudentDetail() of student controller");
		StudentDetailDelegate studentDelegate = new StudentDetailDelegate();
		return studentDelegate.addStudentDetail(student);
	}

	@PostMapping("/students/achievements")
	public Boolean addAchievementDetail(@RequestBody Profile profile) {
		System.out.println("enter");
		log.info("Entering addAchievementDetail() of student controller");
		StudentDetailDelegate studentDelegate = new StudentDetailDelegate();
		return studentDelegate.addProfileDetail(profile, ApplicationConstants.ACHIEVEMENT);
	}

	@PutMapping("/students/{id}")
	public Boolean updateStudentDetail(@RequestBody Student student) {
		log.info("Entering addStudentDetail() of student controller");
		System.out.println(student.getCgpa());
		StudentDetailDelegate studentDelegate = new StudentDetailDelegate();
		return studentDelegate.updateStudentDetail(student);
	}

	@DeleteMapping("/students/{id}")
	public Boolean deleteStudentDetail(@PathVariable String id) {
		StudentDetailDelegate studentDelegate = new StudentDetailDelegate();
		return studentDelegate.deleteStudentDetail(String.valueOf(id));

	}

}
