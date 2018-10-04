package io.ztech.autorate.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.Statistics;
import io.ztech.autorate.dao.FetchStatisticsDAO;

public class FetchStatisticsDelegate {

	FetchStatisticsDAO fetchStatisticsDAO = new FetchStatisticsDAO();

	public ArrayList<Statistics> displayAllStatisticsCar(Specification specification)throws Exception {
		return fetchStatisticsDAO.displayAllStatisticsCar(specification);
	}

	public boolean displayTotalSalesAllCarsYear(Statistics statistics) throws SQLException {
		return fetchStatisticsDAO.displayTotalSalesAllCarsYear(statistics);
	}

	public boolean displayTotalSalesCar(Statistics statistics, Specification specification) throws SQLException {
		return fetchStatisticsDAO.displayTotalSalesCar(statistics, specification);
	}

}
