package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.PlacedDetail;
import com.example.demo.beans.Student;
import com.example.demo.delegate.RetrieveDetailDelegate;

@RestController
@RequestMapping("/demo")
public class PlacementRecordController {
	@GetMapping("/placement-record")
	public ArrayList<PlacedDetail> getPlacementRecord(){
		RetrieveDetailDelegate retrieveDetail=new RetrieveDetailDelegate();
		System.out.println( retrieveDetail.getPlacementDetails());
		return retrieveDetail.getPlacementDetails();
		
	}
	@GetMapping("/placement-record/students/{id}")
	public ArrayList<Student> getPlacedStudentDetails(@PathVariable String id){
		RetrieveDetailDelegate retrieveDetail=new RetrieveDetailDelegate();
		System.out.println( retrieveDetail.getPlacementDetails());
		return retrieveDetail.getStudentsPlacedInASpecificCompany(id);

	}

}
