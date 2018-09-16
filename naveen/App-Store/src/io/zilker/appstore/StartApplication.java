package io.zilker.appstore;

import io.zilker.appstore.ui.*;

public class StartApplication {

	public static void main(String[] args) {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");
		MainUI mainUI = new MainUI();
		mainUI.mainOptions();
	}

}
