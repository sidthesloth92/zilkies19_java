package io.ztech.onlinebidding.utils;

import java.util.Scanner;
import java.util.logging.Logger;

public class GetItemCategoryId {
	public static Logger logger = Logger.getLogger("GetItemCategoryID");
	Scanner input = new Scanner(System.in);

	public String getId(String comment1) {

		String id;
		do {
			logger.info(comment1);
			id=input.nextLine();
		} while (id.equals(""));
		return id;
	}

}
