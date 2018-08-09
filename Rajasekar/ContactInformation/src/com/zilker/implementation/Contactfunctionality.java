package com.zilker.implementation;

import java.sql.*;
import java.util.ArrayList;

import com.zilker.beans.ContactDetails;

public interface Contactfunctionality {
	public static void insertcontact(ContactDetails c) {
	}

	public static void insertemail(int a, ArrayList<String> al) {
	}

	public static void insertoffice(int a, ArrayList<String> al) {
	}

	public static void inserthome(int a, ArrayList<String> al) {
	}

	public static void insertmobile(int a, ArrayList<String> al) {
	}

	public static void displayinfo(Connection conn, PreparedStatement prep, ResultSet rs) {
	}

	public static void sortfname() {
	}

	public static void sortlname() {
	}

	public static int showbase(String a, String b) {
		return 0;
	}

	public static int displaymobile(int a) {
		return a;
	}

	public static void updatemobile(String a, String b, int c) {
	}

	public static int displayoffice(int a) {
		return a;
	}

	public static void updateoffice(String a, String b, int c) {
	}

	public static int displayhome(int a) {
		return a;
	}

	public static void updatehome(String a, String b, String c, int d) {
	}

	public static ArrayList<String> getmobiledetails(String a, String b, ArrayList<String> al) {
		return al;
	}

	public static ArrayList<String> getofficedetails(String a, String b, ArrayList<String> al) {
		return al;
	}

	public static ArrayList<String> gethomedetails(String a, String b, String c, ArrayList<String> al) {
		return al;
	}

	public static int displayemail(int a) {
		return a;
	}

	public static ArrayList<String> getemaildetails(String a, ArrayList<String> al) {
		return al;
	}

	public static void updateemail(String a, int b) {
	}

	public static void updatefname(String a, String b) {
	}

	public static void updatelname(String a, String b) {
	}
}
