package io.ztech.onlinebidding.delegate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import io.ztech.onlinebidding.dao.IdRetrieval;
import io.ztech.onlinebidding.dao.InsertBidLog;
import io.ztech.onlinebidding.dao.PriceRetrieval;
import io.ztech.onlinebidding.dao.TimeRetrieval;

public class BidAnItemDelegate {
	public static Logger logger = Logger.getLogger("BidAnItemDelegate");
	IdRetrieval idRetreive = new IdRetrieval();
	PriceRetrieval priceRetreive = new PriceRetrieval();
	TimeRetrieval timeRetreive = new TimeRetrieval();
	public int customerId;
	public float price;
	ArrayList<Date> timeList = new ArrayList<>();
	public Date startTime, endTime;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date currenttime = new Date();
	InsertBidLog insertCurrentBidDetails = new InsertBidLog();
	int bidderId, customerIdOfBidder;

	public boolean checkApplicant(String bidItemId, String username) {
		customerId = idRetreive.customerIdRetrieve(username);
		bidderId = idRetreive.bidderIdRetrieve(bidItemId);
		customerIdOfBidder = idRetreive.customerIdOfBidder(bidderId);
		if (customerId == customerIdOfBidder) {
			return false;
		} else {

			return true;
		}
	}

	public boolean checkAvailable(String bidItemId, String username) {
		customerId = idRetreive.customerIdRetrieve(username);
		if (customerId != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkTimeAvailable(String bidItemId, String userName) {
		price = priceRetreive.priceRetrive(bidItemId);
		timeList = timeRetreive.retreiveTime(bidItemId);
		startTime = timeList.get(0);
		endTime = timeList.get(1);
		long totalDifference = endTime.getTime() - startTime.getTime();
		long currentTImeDifference = endTime.getTime() - currenttime.getTime();
		long totalDifferenceMinutes = currentTImeDifference / (60 * 1000);
		long currentTimeDifferenceMinutes = totalDifference / (60 * 1000);
		if (totalDifferenceMinutes > 0 && totalDifferenceMinutes <= currentTimeDifferenceMinutes) {
			return true;
		} else {
			return false;
		}
	}

	public void calculate(String bidItemId, String username) {
		customerId = idRetreive.customerIdRetrieve(username);
		price = priceRetreive.priceRetrive(bidItemId);
		timeList = timeRetreive.retreiveTime(bidItemId);
		startTime = timeList.get(0);
		endTime = timeList.get(1);
		long totalDifference = endTime.getTime() - startTime.getTime();
		long currentTImeDifference = endTime.getTime() - currenttime.getTime();
		long totalDifferenceMinutes = currentTImeDifference / (60 * 1000);
		long currentTimeDifferenceMinutes = totalDifference / (60 * 1000);
		if (totalDifferenceMinutes < (currentTimeDifferenceMinutes / 4)) {
			price += (price * 2 / 5);
		} else if (totalDifferenceMinutes < (currentTimeDifferenceMinutes / 2)) {
			price = price + (price / 5);
		} else {
			price += price / 10;
		}
		insertCurrentBidDetails.insertBidLog(Integer.parseInt(bidItemId), customerId, price);

	}
}
