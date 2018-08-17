package com.zilker.ternarysearch;

import java.util.Arrays;
import java.util.Scanner;

public class TernarySearch {
	static int ternarySearch(int low, int high, int arr[], int ele) {
		if (low <= high) {
			int mid1 = low + (high - low) / 3;
			int mid2 = low + (high - mid1) / 3;
			if (arr[mid1] == ele)
				return mid1;
			if (arr[mid2] == ele)
				return mid2;
			if (ele < arr[mid1])
				return ternarySearch(low, mid1 - 1, arr, ele);
			if (ele > arr[mid2])
				return ternarySearch(mid2 + 1, high, arr, ele);
			else
				return ternarySearch(mid1 + 1, mid2 - 1, arr, ele);
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = in.nextInt();
		int ele = in.nextInt();
		Arrays.sort(arr);
		int res;
		System.out.println((res = ternarySearch(0, arr.length - 1, arr, ele)) == -1 ? "Element not present!"
				: "Element is present in " + res + " index");
	}
}
