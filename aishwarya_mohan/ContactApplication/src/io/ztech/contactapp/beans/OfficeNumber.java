package io.ztech.contactapp.beans;

public class OfficeNumber {
	String extensionNumber;
	String number;
	int office_id;

	void setOfficeNumber(String extensionNumber, String number) {
		this.extensionNumber = extensionNumber;
		this.number = number;
	}

	public String getExtensionNumber() {
		return extensionNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setOffice_id(int office_id) {
		this.office_id = office_id;
	}

	public int getOffice_id() {
		return office_id;
	}

}