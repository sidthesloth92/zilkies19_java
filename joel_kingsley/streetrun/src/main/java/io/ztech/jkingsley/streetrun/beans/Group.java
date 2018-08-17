package io.ztech.jkingsley.streetrun.beans;

import java.io.Serializable;
import java.math.BigInteger;

public class Group  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 528169319930729346L;
	
	BigInteger group_id;
	String group_name;
	
	public BigInteger getGroup_id() {
		return group_id;
	}
	public void setGroup_id(BigInteger group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
}
