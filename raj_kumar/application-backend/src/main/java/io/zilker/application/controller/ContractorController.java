package io.zilker.application.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.AvailableProject;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Name;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.beans.Response;
import io.zilker.application.delegate.ContractorDelegate;

@Controller
@RequestMapping("/contractors")
public class ContractorController {
	ContractorDelegate contractorDelgate = new ContractorDelegate();

	@PostMapping("/register")
	@ResponseBody
	public void userRegistration(@RequestBody Contractor contractor) throws Exception {
		contractorDelgate.contractorCreationService(contractor);
	}

	@GetMapping("/{contractorID}/projects")
	@ResponseBody
	public ArrayList<ApprovedProject> getContractorProject(@PathVariable("contractorID") Integer contractorID) {
		return contractorDelgate.getContractorProject(contractorID);
	}

	@GetMapping("/{contractorID}/delayed-projects")
	@ResponseBody
	public ArrayList<ApprovedProject> delayedProjects(@PathVariable("contractorID") Integer contractorID) {
		return contractorDelgate.delayedProjects(contractorID);
	}

	@GetMapping("/available-projects")
	@ResponseBody
	public ArrayList<AvailableProject> getAvailableProjects() {
		return contractorDelgate.displayProjects();
	}

	@GetMapping("/{projectID}/responses")
	@ResponseBody
	public ArrayList<Response> getResponses(@PathVariable("projectID") Integer projectID) {
		return contractorDelgate.getResponses(projectID);
	}

	@GetMapping("/{contractorID}/requested")
	@ResponseBody
	public ArrayList<RequestedProject> getRequestedProjects(@PathVariable("contractorID") Integer contractorID) {
		return contractorDelgate.getRequestedProjects(contractorID);
	}

	@PutMapping("{projectID}/completed")
	@ResponseBody
	public void projectCompleted(@PathVariable("projectID") Integer projectID) {
		contractorDelgate.projectCompleted(projectID);
	}

	@GetMapping("/{contractorID}")
	@ResponseBody
	public Name getContractorName(@PathVariable("contractorID") Integer contractorID) {
		return contractorDelgate.getContractorName(contractorID);
	}

	@PostMapping("/add-response")
	@ResponseBody
	public void addResponse(@RequestBody Response response) throws Exception {
		contractorDelgate.addResponseService(response.getProjectID(), response.getID(), response.getResponseText());
	}

	@PostMapping("/request-tender")
	@ResponseBody
	public void requestTender(@RequestBody RequestedProject projectRequest) throws Exception {
		contractorDelgate.requestTender(projectRequest.getAvailableID(), projectRequest.getContrID(),
				projectRequest.getStartDate(), projectRequest.getEndDate(), projectRequest.getEstCost().longValue());
	}
}
