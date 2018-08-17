package io.ztech.onlinebidding.utils;

import java.util.Scanner;
import java.util.logging.Logger;

public class Validator {
	public static Logger logger = Logger.getLogger("Validator");
	static Scanner input = new Scanner(System.in);
	static boolean flag = true;

	public static Boolean validator(String field, String regex) {
		if (field.matches(regex)) {
			return false;
		}
		return true;
	}
}
