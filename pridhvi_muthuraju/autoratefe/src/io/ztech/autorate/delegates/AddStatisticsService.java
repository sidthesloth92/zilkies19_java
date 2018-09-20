package io.ztech.autorate.delegates;

import java.sql.SQLException;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.Statistics;

public class AddStatisticsService {

	public boolean addStatistics(Specification specification, Statistics statistics) throws SQLException {
//		return addStatisticsDelegate.addStatistics(specification, statistics);
		return true;
	}
}
