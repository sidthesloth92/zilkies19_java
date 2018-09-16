package io.ztech.jkingsley.hrmanagement.beans.objects;

import java.math.BigInteger;

import io.ztech.jkingsley.hrmanagement.beans.types.AdminResponse;
import io.ztech.jkingsley.hrmanagement.beans.types.EmpResponse;
import io.ztech.jkingsley.hrmanagement.beans.types.GrievanceStatus;

public class Grievance {
	BigInteger grievance_id;
	BigInteger emp_id;
	String grievance_message;
	EmpResponse emp_response;
	AdminResponse admin_response;
	GrievanceStatus grievance_status;
}
