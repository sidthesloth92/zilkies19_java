package io.ztech.onlinebidding.delegate;

import io.ztech.onlinebidding.bean.CustomerDetail;
import io.ztech.onlinebidding.dao.InsertNewCustomer;
import io.ztech.onlinebidding.dao.UserNameRetrieval;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class NewUserDelegate {
	static Cipher cipher;
	InsertNewCustomer newUserDao = new InsertNewCustomer();
	UserNameRetrieval retrieveUserName = new UserNameRetrieval();

	private static final byte[] keyValue = new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't',
			'K', 'e', 'y' };

	public CustomerDetail userPasswordEncode(CustomerDetail customerDetail) throws Exception {

		try {
			customerDetail.setPassword(encodePassword(customerDetail.getPassword()));
			return customerDetail;
		} catch (Exception e) {
			throw e;
		}
	}

	public String encodePassword(String password) throws Exception {
		SecretKey secretKey = new SecretKeySpec(keyValue, "AES");
		cipher = Cipher.getInstance("AES");
		byte[] plainTextByte = password.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}

	public Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, "AES");
		return key;

	}

	public void insertDetailsToDb(CustomerDetail customerdetail) throws Exception {
		try {
			newUserDao.insertNewUserDetails(customerdetail);
		} catch (Exception e) {
			throw e;
		}

	}

	public boolean userNameAvailable(String userName) throws Exception {
		try {
			if (retrieveUserName.userNameRetrieve(userName)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
