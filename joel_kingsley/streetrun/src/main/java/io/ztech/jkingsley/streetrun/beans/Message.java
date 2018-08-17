package io.ztech.jkingsley.streetrun.beans;

import java.io.Serializable;
import java.math.BigInteger;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5946550297399825839L;
	
	BigInteger message_id;
	String message;
	BigInteger owner_id;
	BigInteger receiver_id;
	Boolean deleted;
	
	public BigInteger getMessage_id() {
		return message_id;
	}
	public void setMessage_id(BigInteger message_id) {
		this.message_id = message_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BigInteger getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(BigInteger owner_id) {
		this.owner_id = owner_id;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}
