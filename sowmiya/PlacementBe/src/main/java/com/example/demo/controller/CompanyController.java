package com.example.demo.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Company;
import com.example.demo.delegate.CompanyDetailDelegate;
import com.example.demo.delegate.RetrieveDetailDelegate;

@RestController
@RequestMapping("/demo")
public class CompanyController {
	private RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
	private CompanyDetailDelegate companyDelegate = new CompanyDetailDelegate();

	@GetMapping("/company")
	public ArrayList<Company> getCompany() {
		return retrieveDelegate.getCompanyDetails();
	}

	@GetMapping("/company/{id}")
	public Company getSpecificCompany(@PathVariable int id) {
		Company company = new Company();
		company.setCompanyId(id);
		return retrieveDelegate.getSpecificCompanyDetail(company);
	}

	@PostMapping("/company")
	public Boolean addCompany(@RequestBody Company company) {
		return companyDelegate.addCompanyDetail(company);
	}

	@PutMapping("/company/{id}")
	public Boolean updateCompany(@RequestBody Company company) {
		return companyDelegate.updateCompany(company);

	}

	@DeleteMapping("/company/{id}")
	public Boolean DeleteCompany(@PathVariable int id) {
		return companyDelegate.deleteCompany(String.valueOf(id));
	}

}
