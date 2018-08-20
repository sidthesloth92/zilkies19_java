package io.ztech.fitnessapplication;

import io.ztech.fitnessapplication.ui.StartPageUI;

public class DriverClass {
	public static void main(String args[]) {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");
		// new ScanInputUI().scanInput("enter name", "[a-zA-Z]+");
		new StartPageUI().startUpPage();
	}

}
//%1$tT %4$s 