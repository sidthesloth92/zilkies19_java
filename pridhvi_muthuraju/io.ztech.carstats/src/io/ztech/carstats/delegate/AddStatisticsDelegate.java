package io.ztech.carstats.delegate;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.dao.AddStatisticsDAO;

public class AddStatisticsDelegate {
	AddStatisticsDAO asdao=new AddStatisticsDAO();
	public boolean addStatistics(Specification specification, Statistics statistics) {
		return asdao.addStatistics(specification, statistics);
	}
}
