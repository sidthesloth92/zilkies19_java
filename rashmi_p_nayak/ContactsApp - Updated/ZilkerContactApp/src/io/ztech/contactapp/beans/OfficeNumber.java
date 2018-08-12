package io.ztech.contactapp.beans;

//=========================================================================================================================================
//OFFICE NUMBER CLASS - STORES ALL OFFICE NUMBERS
//=========================================================================================================================================
public class OfficeNumber extends Number {
	int oId;
	String extension;
	String areaCode;

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
