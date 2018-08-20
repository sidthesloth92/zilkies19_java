package io.ztech.carstats.delegate;

import java.sql.SQLException;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.dao.AddStatisticsDAO;

public class AddStatisticsDelegate {
	AddStatisticsDAO addStatisticsDAO=new AddStatisticsDAO();
	public boolean addStatistics(Specification specification, Statistics statistics) throws SQLException {
		return addStatisticsDAO.addStatistics(specification, statistics);
	}
}
