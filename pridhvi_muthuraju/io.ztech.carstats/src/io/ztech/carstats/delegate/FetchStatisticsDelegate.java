package io.ztech.carstats.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.dao.FetchStatisticsDAO;

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
