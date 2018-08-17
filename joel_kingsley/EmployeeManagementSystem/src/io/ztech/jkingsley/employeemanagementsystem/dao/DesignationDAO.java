package io.ztech.jkingsley.employeemanagementsystem.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.jkingsley.employeemanagement.constants.Fields;
import io.ztech.jkingsley.employeemanagement.constants.Query;
import io.ztech.jkingsley.employeemanagement.utils.DBUtils;
import io.ztech.jkingsley.employeemanagement.utils.PersistenceException;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Designation;

public class DesignationDAO {
	public ArrayList<Designation> findAllDesignations() throws PersistenceException {
		ArrayList<Designation> designations = new ArrayList<>();

		Connection connection = DBUtils.getConnection();
		
		ResultSet rs = null;
		
		try {
			PreparedStatement listDesignations = connection.prepareStatement(Query.LIST_ALL_DESIGNATIONS);

			rs = listDesignations.executeQuery();

			while (rs.next()) {
				Designation designation = new Designation();
		
				designation.setDesignation_id(BigInteger.valueOf(rs.getLong(Fields.DESIGNATION_KEY_ID)));
				designation.setDesgination_name(rs.getString(Fields.DESIGNATION_KEY_NAME));
				
				designations.add(designation);
			}

			return designations;
		} catch (SQLException e) {
			//throw new PersistenceException("Error in finding all designations",e);
			e.printStackTrace();
			return null;
		} finally {
			DBUtils.closeConnection(connection,rs);
		}

	}
}
