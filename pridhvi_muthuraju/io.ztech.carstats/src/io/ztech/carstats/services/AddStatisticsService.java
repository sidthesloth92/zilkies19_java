package io.ztech.carstats.services;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.delegate.AddStatisticsDelegate;

public class AddStatisticsService {

	AddStatisticsDelegate asDelegate= new AddStatisticsDelegate();
	public boolean addStatistics(Specification specification, Statistics statistics) {
		return asDelegate.addStatistics(specification, statistics);
	}
}
