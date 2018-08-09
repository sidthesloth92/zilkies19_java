package io.ztech.contacts.beans;

//store home number details
public class HomeDetails extends NumberDetails{
	int hId;
	String areaCode;

	public int gethId() {
		return hId;
	}

	public void sethId(int hId) {
		this.hId = hId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
