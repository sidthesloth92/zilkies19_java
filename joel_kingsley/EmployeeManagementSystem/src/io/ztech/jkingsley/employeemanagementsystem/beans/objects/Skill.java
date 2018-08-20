package io.ztech.jkingsley.employeemanagementsystem.beans.objects;

import java.math.BigInteger;

public class Skill {
	BigInteger skill_id;
	String skill_name;

	public BigInteger getSkill_id() {
		return skill_id;
	}

	public void setSkill_id(BigInteger skill_id) {
		this.skill_id = skill_id;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}

}
