package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Student;
import com.example.demo.delegate.RetrieveDetailDelegate;

@RestController
@RequestMapping("/placement")
public class RegistrationListController {
	
	@GetMapping("/registration-list/{companyId}")
	public ArrayList<Student> getRegistrationList(@PathVariable String companyId){
		RetrieveDetailDelegate retrieveDetail=new RetrieveDetailDelegate();
		System.out.println( retrieveDetail.getPlacementDetails());
		return retrieveDetail.getEligiblityList(companyId);
	}

}
