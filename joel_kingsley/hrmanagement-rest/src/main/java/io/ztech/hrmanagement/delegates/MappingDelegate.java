package io.ztech.hrmanagement.delegates;

import java.math.BigInteger;

import io.ztech.hrmanagement.beans.objects.Designation;
import io.ztech.hrmanagement.dao.DesignationDAO;
import io.ztech.hrmanagement.utils.PersistenceException;

public class MappingDelegate {
	public Designation findDesignationOfDesignationID(BigInteger designationID) throws PersistenceException {
		DesignationDAO designationDAO = new DesignationDAO();
		return designationDAO.findDesignationOfDesignationID(designationID);
	}
}
