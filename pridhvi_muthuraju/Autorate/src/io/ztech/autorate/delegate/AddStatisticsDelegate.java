package io.ztech.autorate.delegate;

import java.sql.SQLException;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.Statistics;
import io.ztech.autorate.dao.AddStatisticsDAO;

public class AddStatisticsDelegate {
	AddStatisticsDAO addStatisticsDAO=new AddStatisticsDAO();
	public boolean addStatistics(Specification specification, Statistics statistics) throws SQLException {
		return addStatisticsDAO.addStatistics(specification, statistics);
	}
}
