package com.zilker.exercise.leapgame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LeapGame {

	public static boolean playGame(int[] gameArray, int leap) {

		Queue<Integer> q = new LinkedList<>();
		ArrayList<Integer> al = new ArrayList<>();
		q.add(0);
		while (!q.isEmpty()) {
			int temp = q.poll();
			if (temp >= gameArray.length) {
				return true;
			}
			if ((temp + 1) < gameArray.length && gameArray[temp + 1] == 0 && !al.contains(temp + 1)) {
				q.add(temp + 1);
				al.add(temp + 1);
			}
			if ((temp - 1) >= 0 && gameArray[temp - 1] == 0 && !al.contains(temp - 1)) {
				q.add(temp - 1);
				al.add(temp - 1);
			}
			if (temp + leap >= gameArray.length || gameArray[temp + leap] == 0 && !al.contains(temp + leap)) {
				q.add(temp + leap);
				al.add(temp + leap);
			}
		}
		return false;
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), leap = sc.nextInt();
		int[] gameArray = new int[n];
		for (int i = 0; i < n; i++) {
			gameArray[i] = sc.nextInt();
		}
		System.out.println(playGame(gameArray, leap));
	}
}
