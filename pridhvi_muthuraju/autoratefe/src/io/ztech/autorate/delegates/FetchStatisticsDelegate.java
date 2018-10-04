package io.ztech.autorate.delegates;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.Statistics;

public class FetchStatisticsDelegate {


	public ArrayList<Statistics> displayAllStatisticsCar(Specification specification) throws Exception {
//		return fetchStatisticsDelegate.displayAllStatisticsCar(specification);
		return null;
	}

	public boolean displayTotalSalesAllCarsYear(Statistics statistics) throws SQLException {
//		return fetchStatisticsDelegate.displayTotalSalesAllCarsYear(statistics);
		return true;
	}

	public boolean displayTotalSalesCar(Statistics statistics, Specification specification) throws SQLException {
//		return fetchStatisticsDelegate.displayTotalSalesCar(statistics, specification);
		return true;
	}
}
