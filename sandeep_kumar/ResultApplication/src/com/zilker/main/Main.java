package com.zilker.main;

import com.zilker.ui.*;
public class Main{
	public static void main(String args[]) {
		System.setProperty("java.util.logging.SimpleFormatter.format","%5$s%6$s%n");
		LoginUI login=new LoginUI();
		login.login();
	}
}
