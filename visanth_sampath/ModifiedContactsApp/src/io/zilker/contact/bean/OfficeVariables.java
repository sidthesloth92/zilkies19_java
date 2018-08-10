package io.zilker.contact.bean;

public class OfficeVariables {
	private String officeNumber , extension;
	
	//setters
	public void setMobile(String onumber,String extensionParam) {
		officeNumber = onumber;
		extension = extensionParam;
	}
	
	//getters
	public String getOfficeNumber() {
		return officeNumber;
	}
	public String getExtension() {
		return extension;
	}

}
