package io.ztech.carstats.services;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.delegate.FetchStatisticsDelegate;

public class FetchStatisticsService {

	FetchStatisticsDelegate fetchStatisticsDelegate = new FetchStatisticsDelegate();

	public ArrayList<Statistics> displayAllStatisticsCar(Specification specification) throws Exception {
		return fetchStatisticsDelegate.displayAllStatisticsCar(specification);
	}

	public boolean displayTotalSalesAllCarsYear(Statistics statistics) throws SQLException {
		return fetchStatisticsDelegate.displayTotalSalesAllCarsYear(statistics);
	}

	public boolean displayTotalSalesCar(Statistics statistics, Specification specification) throws SQLException {
		return fetchStatisticsDelegate.displayTotalSalesCar(statistics, specification);
	}
}
