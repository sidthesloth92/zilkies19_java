package io.ztech.autorate.services;

import java.sql.SQLException;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.Statistics;
import io.ztech.autorate.delegate.AddStatisticsDelegate;

public class AddStatisticsService {

	AddStatisticsDelegate addStatisticsDelegate= new AddStatisticsDelegate();
	public boolean addStatistics(Specification specification, Statistics statistics) throws SQLException {
		return addStatisticsDelegate.addStatistics(specification, statistics);
	}
}
