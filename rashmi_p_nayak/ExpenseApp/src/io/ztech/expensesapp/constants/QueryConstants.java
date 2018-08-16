package io.ztech.expensesapp.constants;

public class QueryConstants {
	public static final String INSERT_INTO_USERS = "insert into Users(user_name,email_id,password,expense_limit) values(?,?,md5(?),?)";
	public static final String CHECK_EXISTING_USERNAME = "select count(*) from Users where user_name = ?";
	public static final String VALIDATE_USER = "select user_name, email_id, expense_limit,u_id from Users where ( user_name = ? or email_id = ? )and password = md5(?)";
	public static final String INSERT_INTO_EXPENSES = "insert into Expenses(u_id,category_id,type_id,description,amount) values(?,?,?,?,?)";
	public static final String SELECT_ALL_EXPENSES = "select category_id,type_id,description,amount,e_id from Expenses where u_id = ?";
	public static final String SELECT_ALL_GROUP_EXPENSES = "select category_id,type_id,description,amount,Group_Expenses.payment_id from Group_Expenses join Expense_Members on Group_Expenses.payment_id = Expense_Members.payment_id where Expense_Members.u_id = ? and pending = 0";
	public static final String INSERT_INTO_GROUPS = "insert into Groups(group_name) values(?)";
	public static final String SELECT_RECENT_GID = "select g_id from Groups order by g_id desc limit 1";
	public static final String INSERT_INTO_GROUP_MEMBERS = "insert into Group_Members(g_id,u_id) values(?,(select u_id from Users where user_name = ?))";
}
