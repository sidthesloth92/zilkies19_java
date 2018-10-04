package io.ztech.hrmanagement.beans.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int status;
	ArrayList<Object> objects;
	
	public ResponseObject() {
		super();
	}

	public ResponseObject(int status, ArrayList<Object> objects) {
		super();
		this.status = status;
		this.objects = objects;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArrayList<Object> getObjects() {
		return objects;
	}
	public void setObjects(ArrayList<Object> objects) {
		this.objects = objects;
	}
	
}
