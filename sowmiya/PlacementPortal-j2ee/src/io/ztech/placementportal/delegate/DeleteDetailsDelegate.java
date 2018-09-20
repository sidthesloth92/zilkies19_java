package io.ztech.placementportal.delegate;

import java.sql.SQLException;

import com.sun.org.apache.bcel.internal.generic.DALOAD;

import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dao.DeleteDetailsDao;

public class DeleteDetailsDelegate {
     DeleteDetailsDao deleteDao=new DeleteDetailsDao();
	public boolean deleteStudent(String id) throws SQLException{
		return deleteDao.deleteStudent(id);
        
	}
	public boolean deleteCompany(String id) throws SQLException {
		return deleteDao.deleteCompany(id);
	}
	public boolean deleteProfile(String profile_id, String category) throws SQLException {
		if (category.equals(ApplicationConstants.ACHIEVEMENT))
			return deleteDao.deleteProfile(profile_id, SqlConstants.DELETEACHIEVEMENT);
		if (category.equals(ApplicationConstants.PROJECT))
			return deleteDao.deleteProfile(profile_id, SqlConstants.DELETEPROJECT);
		if (category.equals(ApplicationConstants.CERTIFICATIONS))
			return deleteDao.deleteProfile(profile_id, SqlConstants.DELETECOURSE);
		return false;
	}
}
