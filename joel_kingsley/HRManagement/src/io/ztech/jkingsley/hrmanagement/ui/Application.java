package io.ztech.jkingsley.hrmanagement.ui;

import io.ztech.jkingsley.hrmanagement.ui.GatewayUI.MenuOption;

public class Application {
	public static void main(String args[]) {
		
		 System.setProperty("java.util.logging.SimpleFormatter.format", 
		            "%5$s%6$s%n");
		 
		while (true) {
			GatewayUI gateway = new GatewayUI();
			gateway.displayMenu();
			MenuOption menuOption = gateway.getMenuOption();
			gateway.execute(menuOption);
		}
	}
}
