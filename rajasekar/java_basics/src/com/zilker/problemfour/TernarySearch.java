package com.zilker.problemfour;

import java.util.*;

public class TernarySearch {
	public static int search(int arr[], int low, int high, int ele) {
		if (low <= high) {
			int part1 = low + (high - low) / 3;
			int part2 = part1 + (high - low) / 3;
			if (arr[part1] == ele)
				return part1;
			if (arr[part2] == ele)
				return part2;
			if (arr[part1] > ele)
				return search(arr, low, part1 - 1, ele);
			if (arr[part2] < ele)
				return search(arr, part2 + 1, high, ele);
			return search(arr, part1 + 1, part2 - 1, ele);
		}
		return -1;
	}

	public static int[] sort(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the no.of elements");
		int n = in.nextInt();
		int arr[] = new int[n];
		System.out.println("Enter the array Elements");
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		arr = sort(arr);
		System.out.println("Enter the element to search");
		int ele = in.nextInt();
		int index = search(arr, 0, n - 1, ele);
		if (index == -1) {
			System.out.println("Element not found");
		} else {
			System.out.println("Element found at index: " + index);
		}
	}
}
