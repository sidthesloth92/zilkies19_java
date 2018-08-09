package com.zilker.Ternary;

import java.util.Arrays;
import java.util.Scanner;

public class Ternary {
	static void ternarySearch(int arr[], int firstptr, int lastptr, int key) {
		int middle, midoneptr, midtwoptr, total;
		total = lastptr - firstptr + 1;
		middle = total / 3;
		midoneptr = firstptr + middle;
		midtwoptr = lastptr - middle;
		if (total == 1 && arr[firstptr] != key) {
			System.out.println("Element not Found");
			return;
		}
		if (arr[firstptr] == key) {
			System.out.println("Element Found at index" + firstptr);
			return;
		}
		if (arr[midoneptr] == key) {
			System.out.println("Element Found at index" + midoneptr);
			return;
		}
		if (arr[midtwoptr] == key) {
			System.out.println("Element Found at index" + midtwoptr);
			return;
		}
		if (arr[midoneptr] < key) {
			if (midtwoptr > key) {
				ternarySearch(arr, midoneptr, midtwoptr, key);
			} else {
				ternarySearch(arr, midtwoptr, lastptr, key);
			}

		} else {
			ternarySearch(arr, firstptr, midoneptr, key);
		}

	}

	public static void main(String[] args) {
		int i, n;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int[] array = new int[n];
		for (i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		Arrays.sort(array);
		System.out.println("Enter the key\n");
		int key = sc.nextInt();

		ternarySearch(array, 0, n - 1, key);
	}

}
