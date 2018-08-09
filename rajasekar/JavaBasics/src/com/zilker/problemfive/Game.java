package com.zilker.problemfive;

import java.util.*;

public class Game {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the value of n and leap");
		int n = in.nextInt();
		int leap = in.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		for (int i = 0; i < n;) {
			if (i + leap >= n - 1) {
				System.out.println("WIN");
				System.exit(0);
			} else if (i + leap <= n - 1 && arr[i + leap] == 0) {
				i = i + leap;
			} else if (i + 1 < n - 1 && arr[i + 1] == 0) {
				i = i + 1;
			} else {
				System.out.println("LOSE");
				System.exit(0);
			}
		}
	}
}