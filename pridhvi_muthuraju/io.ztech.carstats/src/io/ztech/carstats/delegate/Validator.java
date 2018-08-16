package io.ztech.carstats.delegate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import io.ztech.carstats.constants.AppConstants;
import io.ztech.carstats.dbutils.DBUtils;

public class Validator {

	public static boolean validateUsername(String username) {
		// return Pattern.matches("^[a-z0-9_-]{3,15}$", username);
		return true;
	}

	public static boolean validatePassword(String password) {
		// return Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})", password);
		return true;
	}
}
