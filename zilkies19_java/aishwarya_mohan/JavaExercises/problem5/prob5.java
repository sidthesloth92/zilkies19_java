package com.zilkertech.problem5;

import java.util.Scanner;

public class prob5 {
	static int ternarySearch(int l, int r, int x, int arr[]) {
		if (r >= l) {
			int mid1 = l + (r - l) / 3;
			int mid2 = r - (r - l) / 3;
			if (arr[mid1] == x) {
				return mid1;
			}
			if (arr[mid2] == x) {
				return mid2;
			}
			if (x < arr[mid1]) {
				return ternarySearch(l, mid1 - 1, x, arr);
			} else if (x > arr[mid2]) {
				return ternarySearch(mid2 + 1, r, x, arr);
			} else {
				return ternarySearch(mid1 + 1, mid2 - 1, x, arr);
			}
		}
		return -1;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int noOfElements = in.nextInt();
		int inputArray[] = new int[noOfElements];
		for (int i = 0; i < noOfElements; i++) {
			inputArray[i] = in.nextInt();
		}
		int key=in.nextInt();
		System.out.println(ternarySearch(0, noOfElements, key, inputArray));
	}
}
