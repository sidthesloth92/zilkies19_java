package io.ztech.fitnessapplication.ui;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import io.ztech.fitnessapplication.constants.DisplayStringConstants;

public class ScanInputUI {
	private Logger logger;
	private Scanner sc;

	public ScanInputUI() {
		logger = Logger.getLogger(ScanInputUI.class.getName());
		sc = new Scanner(System.in);
	}

	public String scanInput(String display, String regex) {
		boolean flag = false;
		String input;
		do {
			try {
				logger.info(display);
				input = sc.next();
				flag = Pattern.matches(regex, input);
				if (flag) {
					return input;
				} else {
					throw new Exception(DisplayStringConstants.INVALID_INPUT_WARNING);
				}
			} catch (Exception e) {
				// logger.info(e + "");
				return scanInput(display, regex);
			}
		} while (!flag);
	}

}
