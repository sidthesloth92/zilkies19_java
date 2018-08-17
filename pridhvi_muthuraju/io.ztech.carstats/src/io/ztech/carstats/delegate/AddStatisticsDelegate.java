package io.ztech.carstats.delegate;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.dao.AddStatisticsDAO;

public class AddStatisticsDelegate {
	AddStatisticsDAO addStatisticsDAO=new AddStatisticsDAO();
	public boolean addStatistics(Specification specification, Statistics statistics) {
		return addStatisticsDAO.addStatistics(specification, statistics);
	}
}
