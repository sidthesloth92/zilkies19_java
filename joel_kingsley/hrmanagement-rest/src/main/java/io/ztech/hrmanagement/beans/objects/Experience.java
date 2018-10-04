package io.ztech.hrmanagement.beans.objects;

import java.math.BigInteger;

public class Experience {
	BigInteger emp_id;
	BigInteger skill_id;
	

	public Experience() {
		super();
		emp_id = BigInteger.ZERO;
		skill_id = BigInteger.ZERO;
	}
	
	

	public Experience(BigInteger skill_id) {
		super();
		this.skill_id = skill_id;
		this.emp_id = BigInteger.ZERO;
	}



	public BigInteger getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(BigInteger emp_id) {
		this.emp_id = emp_id;
	}

	public BigInteger getSkill_id() {
		return skill_id;
	}

	public void setSkill_id(BigInteger skill_id) {
		this.skill_id = skill_id;
	}

}
