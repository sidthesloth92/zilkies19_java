package io.ztech.contactapp.beans;

//=========================================================================================================================================
//HOME NUMBER CLASS - STORES ALL HOME NUMBERS
//=========================================================================================================================================
public class HomeNumber extends Number {
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
