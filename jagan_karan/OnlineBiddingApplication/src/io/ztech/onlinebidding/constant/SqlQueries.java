package io.ztech.onlinebidding.constant;

public interface SqlQueries {

	public static final String INSERT_NEW_CUSTOMER = "INSERT INTO customer_details (firstname,lastname,email,mobilenumber,dateofbirth,typeofuser,username,password) values(?,?,?,?,?,?,?,?)";
	public static final String SELECT_USERNAME_PASSWORD = "SELECT password from customer_details where username=?";
	public static final String SELECT_CATEGORY = "select distinct category_id,category_name from category";
	public static final String SELECT_ITEM = "select item_id,item_name from item where category_id=?";
	public static final String SELECT_BID_ITEM_FROM_BASELOG = "select * from bid_base_price where category_id=? and item_id=?";
	public static final String SELECT_CUSTOMER_ID = "select cust_id from customer_details where username=?";
	public static final String SELECT_PRICE = "select price from bid_base_price where bid_item_id=?";
	public static final String SELECT_TIME = "select starttime,endtime from bid_base_price where bid_item_id=?";
	public static final String SELECT_PRICE_LOG = "select price from bid_log where bid_item_id=?";
	public static final String INSERT_BID_LOG = "insert into bid_log values(?,?,?,?)";
	public static final String INSERT_BIDDER = "insert into bidder(customer_id) values(?)";
	public static final String SELECT_BIDDER = "select max(bidder_id) as max from bidder where customer_id=?";
	public static final String INSERT_BID_BASE = "insert into bid_base_price(bidder_id,item_id,category_id,item_name,price,starttime,endtime) values(?,?,?,?,?,?,?)";
	public static final String SELECT_TYPE_OF_USER = "SELECT typeofuser from customer_details where username=?";
	public static final String SELECT_MAX_PRICE = "SELECT max(price) as max from bid_log where bid_item_id=?";
	public static final String SELECT_BID_FROM_LOG = "select * from bid_log where bid_item_id=? and price=?";
	public static final String SELECT_BIDDER_ID = "select bidder_id from bid_base_price where bid_item_id=?";
	public static final String SELECT_CUSTOMERID_RELEVANT_BIDDERID = "select customer_id from bidder where bidder_id=?";
	public static final String SELECT_ENDED_BID_ID = "select distinct bid_item_id from bid_base_price where endtime<now()";
	public static final String INSERT_FINAL_LOG = "insert into bid_final_log values(?,?,?,?)";
	public static final String SELECT_ITEM_FROM_BASE = "select * from bid_base_price where bid_item_id=?";
	public static final String SELECT_SOLDBID_FROM_FINAL_LOG = "select * from bid_final_log where status=\"SOLD\"";
	public static final String SELECT_UNSOLDBID_FROM_FINAL_LOG = "select * from bid_final_log where status=\"UNSOLD\"";
	public static final String DELETE_BID_FROM_LOG = "delete from bid_log where bid_item_id=?";
}