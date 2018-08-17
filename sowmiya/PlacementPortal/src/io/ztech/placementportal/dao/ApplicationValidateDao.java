package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class ApplicationValidateDao {
    PreparedStatement ps=null;
    ResultSet rs=null;
	public LocalDate getDate(Company company) {
		Connection connection=DbConnection.getConnection();
		try {
			ps=connection.prepareStatement(SqlConstants.GETLASTDATE);
			ps.setInt(1, company.getCompany_id());
			rs=ps.executeQuery();
			if(rs.isBeforeFirst()==false) {
				return (LocalDate)null;	
			}
			rs.next();
			return rs.getDate(1).toLocalDate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DbConnection.closeConnection(rs, ps, connection);
		}
		return null;	
	}
	public boolean checkIsApplied(Company company, String reg_no) {
		Connection connection=DbConnection.getConnection();
        try {
        	ps=connection.prepareStatement(SqlConstants.CHECKAPPLIED);
        	ps.setInt(1, company.getCompany_id());
        	ps.setString(2, reg_no);
        	rs=ps.executeQuery();
        	if(rs.isBeforeFirst())
        		return true;
        	else
        		return false;
        	
        }
        catch(SQLException e) {
        	
        }
        finally {
        	DbConnection.closeConnection(rs, ps, connection);
        }
		return false;
	}

}
