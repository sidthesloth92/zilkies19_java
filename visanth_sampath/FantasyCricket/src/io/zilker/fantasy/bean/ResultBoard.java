package io.zilker.fantasy.bean;

import java.util.ArrayList;

public class ResultBoard {
	private ArrayList<Integer> users = new ArrayList<Integer>();
	private ArrayList<Integer> matchPoints = new ArrayList<Integer>();
	ArrayList<String> names = new ArrayList<String>();
	int index;
	//setters for users
	public void setResultBoard(ArrayList<Integer> users ,ArrayList<String> names ,ArrayList<Integer> matchPoints ) {
		for(index= 0;index<users.size();index++) {
			this.users.add(users.get(index));
			this.matchPoints.add(matchPoints.get(index));
			this.names.add(names.get(index));
		}
	}
	
	//getter for users
	public ArrayList<Integer> getUsers(){
		return users;
	}
	
	//getters for names
	public ArrayList<String> getNames(){
		return names;
	}
	
	
	//getters for match Points
	
	public ArrayList<Integer> getMatchPoints(){
		return matchPoints;
	}

}
