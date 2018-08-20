package io.ztech.placementportal.ui;

import java.util.Scanner;
import java.util.logging.Logger;

public class ScanInput {
	private Logger log;
	private Scanner scan;

	public ScanInput() {
		log = Logger.getLogger("ScanInput.class");
		scan = new Scanner(System.in);
	}

	public String getInput(String regex, String ErrorMsg) {
		String input;
		boolean isValid = false;
		while (!isValid) {
			input = scan.nextLine();
			isValid = ValidateInput.validateDetail(regex, input);
			if (isValid == true) {
				return input;
			}
			log.info(ErrorMsg);

		}
		scan.close();
		return null;

	}
}
