package io.zilker.application.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.delegate.AdminDelegate;
import io.zilker.application.exception.SystemException;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = Logger.getLogger(AdminController.class.getName());
	AdminDelegate adminDelegate = new AdminDelegate();

	@RequestMapping(path = "/add-new-project", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void addNewProject(@RequestBody String request) {
		logger.info("Entering addNewProject");
		try {
			adminDelegate.addNewProject(request);
		} catch (SystemException e) {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info("Leaving addNewProject");
		}
	}

	@RequestMapping(path = "/get-requested-project", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ArrayList<RequestedProject> getRequestedProject() throws Exception {
		logger.info("Entering getRequestedProject");
		AdminDelegate adminDelegate = new AdminDelegate();
		ArrayList<RequestedProject> requestedProject = adminDelegate.displayRequested();
		logger.info("Leaving getRequestedProject");
		return requestedProject;
	}

	@GetMapping("/get-all-contractors")
	@ResponseBody
	public ArrayList<Contractor> getAllContractors() {
		logger.info("Entering getAllContractors");
		ArrayList<Contractor> allContractors = new ArrayList<Contractor>();
		try {
			allContractors = adminDelegate.displayAllContractors();
		} catch (SystemException e) {

		} catch (Exception e) {

		} finally {
			logger.info("Leaving getAllContractors");
		}

		return allContractors;
	}

	@GetMapping("/{contractorID}")
	@ResponseBody
	public Contractor getContractor(@PathVariable("contractorID") String contractorID) {
		logger.info("Entering getContractor");
		Contractor contractor = new Contractor();
		try {
			contractor = adminDelegate.getContractor(Integer.parseInt(contractorID));
		} catch (SystemException e) {

		} catch (Exception e) {

		} finally {
			logger.info("Leaving getContractor");
		}
		return contractor;
	}

	@PostMapping("/approve-project")
	@ResponseBody
	public void userRegistration(@RequestParam("requestId") int requestID) throws Exception {
		logger.info("Entering userRegistration");
		try {
			adminDelegate.approveProject(requestID);
		} catch (SystemException e) {

		} catch (Exception e) {

		} finally {
			logger.info("Leaving userRegistration");
		}
	}

}
