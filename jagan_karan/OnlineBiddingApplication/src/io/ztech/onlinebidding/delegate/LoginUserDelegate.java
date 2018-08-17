package io.ztech.onlinebidding.delegate;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.*;

import io.ztech.onlinebidding.bean.CustomerDetail;
import io.ztech.onlinebidding.dao.LoginPasswordRetrieval;
import io.ztech.onlinebidding.dao.TypeOfUserRetrieval;

public class LoginUserDelegate {
	static Cipher cipher;
	LoginPasswordRetrieval passwordRetrieve = new LoginPasswordRetrieval();
	TypeOfUserRetrieval typeretrieve=new TypeOfUserRetrieval();
	String password,type;
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't',
			'K', 'e', 'y' };

	public CustomerDetail userDetail(CustomerDetail customerDetail) {
		try {
			password = passwordRetrieve.retreivePassword(customerDetail.getUserName());
			if (password != null) {
				password = decode(password);
				customerDetail.setPassword(password);
			} else {
				customerDetail.setPassword(" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerDetail;
	}
	public CustomerDetail userType(CustomerDetail detail) {
		type=typeretrieve.retrieveUserType(detail.getUserName());
		detail.setTypeOfUser(type);
		return detail;
	}

	public String decode(String encryptedText) throws Exception {
		SecretKey secretKey = new SecretKeySpec(keyValue, "AES");
		cipher = Cipher.getInstance("AES");
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}

	public Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, "AES");
		return key;
	}
}
