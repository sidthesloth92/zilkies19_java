package io.zilker.appstore.beans;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int userID, wallet;
	private String fullName, userName, password, userPrivilege;
	private Applications[] downloadedApps, myApps, wishList;

	public Applications[] getWishList() {
		return wishList;
	}

	public void setWishList(Applications[] wishList) {
		this.wishList = wishList;
	}

	public Applications[] getDownloadedApps() {
		return downloadedApps;
	}

	public void setDownloadedApps(Applications[] downloadedApps) {
		this.downloadedApps = downloadedApps;
	}

	public Applications[] getMyApps() {
		return myApps;
	}

	public void setMyApps(Applications[] myApps) {
		this.myApps = myApps;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserPrivilege() {
		return userPrivilege;
	}

	public void setUserPrivilege(String userPrivilege) {
		this.userPrivilege = userPrivilege;
	}

}
