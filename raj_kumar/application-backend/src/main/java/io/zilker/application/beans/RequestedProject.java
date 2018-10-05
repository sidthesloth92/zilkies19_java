package io.zilker.application.beans;

import java.math.BigDecimal;
import java.util.Date;

public class RequestedProject {
	private int requestID, contrID, availableID;
	private Date startDate, endDate;
	private BigDecimal estCost;
	private String projectName, location, description, requestedStatus, contractorName;

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getRequestedStatus() {
		return requestedStatus;
	}

	public void setRequestedStatus(String requestedStatus) {
		this.requestedStatus = requestedStatus;
	}

	public int getAvailableID() {
		return availableID;
	}

	public void setAvailableID(int availableID) {
		this.availableID = availableID;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getContrID() {
		return contrID;
	}

	public void setContrID(int contrID) {
		this.contrID = contrID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getEstCost() {
		return estCost;
	}

	public void setEstCost(BigDecimal estCost) {
		this.estCost = estCost;
	}

}
