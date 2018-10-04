package io.ztech.jkingsley.hrmanagement.delegates;

import java.math.BigInteger;

import io.ztech.jkingsley.hrmanagement.beans.objects.Designation;
import io.ztech.jkingsley.hrmanagement.dao.DesignationDAO;
import io.ztech.jkingsley.hrmanagement.utils.PersistenceException;

public class MappingDelegate {
	public Designation findDesignationOfDesignationID(BigInteger designationID) throws PersistenceException {
		DesignationDAO designationDAO = new DesignationDAO();
		return designationDAO.findDesignationOfDesignationID(designationID);
	}
}
