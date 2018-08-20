package io.ztech.onlinebidding.ui;

import java.util.logging.Logger;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;

public class DisplayException {
	public static Logger logger = Logger.getLogger("DisplayException");

	public static void displayException() {

		logger.info(ConstantDisplayStatement.DATABASE_ERROR);
	}
}
