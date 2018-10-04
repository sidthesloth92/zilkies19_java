package io.ztech.hrmanagement.beans.objects;

import java.math.BigInteger;

import io.ztech.hrmanagement.beans.types.AdminResponse;
import io.ztech.hrmanagement.beans.types.EmpResponse;
import io.ztech.hrmanagement.beans.types.GrievanceStatus;

public class Grievance {
	BigInteger grievance_id;
	BigInteger emp_id;
	String grievance_message;
	EmpResponse emp_response;
	AdminResponse admin_response;
	GrievanceStatus grievance_status;
}
