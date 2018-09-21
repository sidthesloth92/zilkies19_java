package io.zilker.application.beans;

import java.math.BigDecimal;
import java.util.Date;

public class ApprovedProject {
	private int projectID, contrID;
	private String projectName, projectStatus, location, description, contractorName;
	private Date startDate, endDate;
	private BigDecimal estCost;

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
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

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public void setEstCost(BigDecimal bigDecimal) {
		this.estCost = bigDecimal;
	}
}
