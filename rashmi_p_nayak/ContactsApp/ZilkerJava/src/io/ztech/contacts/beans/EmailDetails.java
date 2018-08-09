package io.ztech.contacts.beans;

import java.util.regex.Pattern;

//store email id details
public class EmailDetails {
	int cId;
	int eId;
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

	//validates the entered email
	public boolean isValidatedEmail(String emailId) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (emailId != null) {
			return pat.matcher(emailId).matches();
		}
		return false;

	}

}
