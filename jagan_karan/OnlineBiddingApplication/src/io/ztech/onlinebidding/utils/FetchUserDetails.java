package io.ztech.onlinebidding.utils;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.onlinebidding.utils.Validator;

public class FetchUserDetails {
	static Scanner input = new Scanner(System.in);
	public static Logger logger = Logger.getLogger("FetchUserDetails");

	public String fetchdetails(String regex, String comment1, String comment2) {
		String field;
		boolean flag = true;
		logger.info(comment1);
		do {
			field = input.nextLine();
			if (field.equals("-1")) {
				field = " ";
			}
			flag = Validator.validator(field, regex);
			if (flag == true)
				logger.info(comment2);
		} while (flag);
		return field;
	}

	public String fetchtype(String comment) {
		String type = null, option;
		logger.info(comment);
		option = input.nextLine();
		if (option.equals("1")) {
			type = "admin";
		} else if (option.equals("2")) {
			type = "customer";
		}
		return type;
	}

}
