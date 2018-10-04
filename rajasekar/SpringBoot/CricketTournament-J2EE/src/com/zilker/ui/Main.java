package com.zilker.ui;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import com.zilker.constant.ConsoleStrings;
import com.zilker.ui.DisplayMenu;
import com.zilker.ui.FetchAndDisplay;
import com.zilker.dao.CricketTournamentDAO;

public class Main {
	public static void main(String args[]) throws IOException {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");
		new DisplayMenu().showOption();
	}
}