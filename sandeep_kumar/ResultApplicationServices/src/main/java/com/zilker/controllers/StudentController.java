/*package com.zilker.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.beans.ResultData;
import com.zilker.delegates.StudentDelegator;

@RestController
@RequestMapping("/ResultApplication/Student")
public class StudentController {
	
	StudentDelegator student = new StudentDelegator();
	
	@GetMapping("/results/{studentRegistrationNumber}/{semester}")
	public ArrayList<ResultData>getResultById(@PathVariable("studentRegistrationNumber") long studentRegistrationNumber, @PathVariable("semester") int semester, @RequestParam("flag") int flag) {
		try {
			return student.getResultById(studentRegistrationNumber, semester, flag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/results/{studentRegistrationNumber}/{semester}")
	public ArrayList<ResultData>getResultById(@PathVariable("studentRegistrationNumber") long studentRegistrationNumber, @PathVariable("semester") int semester, @RequestParam("flag") int flag) {
		try {
			return student.getResultById(studentRegistrationNumber, semester, flag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
*/