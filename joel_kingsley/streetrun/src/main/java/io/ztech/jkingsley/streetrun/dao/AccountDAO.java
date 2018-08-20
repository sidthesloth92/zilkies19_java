package io.ztech.jkingsley.streetrun.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import io.ztech.jkingsley.streetrun.beans.Account;

public interface AccountDAO {
	/*BigInteger user_id;
	String user_name;
	String password;
	String email;
	String alternate_email;*/
	
	public ArrayList<Account> getAllAccounts();
	public Account getAccountByUsername(String user_name);
	public Account getAccountByUserId(BigInteger user_id);
	public Account getAccountByEmail(String email);
	
	public boolean insertAccount(Account account);
	public boolean updateAccount(Account account);
	public boolean deleteAccount(Account account);
}
