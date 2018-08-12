package io.ztech.contactapp.beans;

//=========================================================================================================================================
//EMAIL ID CLASS - STORES ALL EMAIL IDS 
//=========================================================================================================================================
public class EmailId {

	int eId;
	int cId;
	String emailId;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
