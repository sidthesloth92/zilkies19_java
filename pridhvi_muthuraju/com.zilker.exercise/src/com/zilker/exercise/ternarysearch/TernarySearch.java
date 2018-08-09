package com.zilker.exercise.ternarysearch;

import java.util.Scanner;

public class TernarySearch {

	public static boolean ternarySearch(int[] array, int key, int start, int end) {
		int mid1 = start + ((end - start) / 3), mid2 = start + (((end - start) / 3) * 2);
		if (array[mid1] == key) {
			return true;
		} else if (array[mid2] == key) {
			return true;
		} else if (array[end - 1] == key) {
			return true;
		} else if (array[mid1] > key) {
			return ternarySearch(array, key, start, mid1);
		} else if (array[mid2] > key) {
			return ternarySearch(array, key, mid1, mid2);
		} else if (array[end - 1] > key) {
			return ternarySearch(array, key, mid2, end);
		}
		return false;
	}
	public static int[] sort(int[] array) {
		for(int i=0;i<array.length;i++) {
			for(int j=1;j<array.length;j++) {
				if(array[j]<array[j-1]) {
					array[j-1]=array[j-1]^array[j]^(array[j]=array[j-1]);
				}
			}
		}
		return array;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter array size: ");
		int arraySize = sc.nextInt();
		int[] array = new int[arraySize];
		System.out.println("Enter array elements");
		for (int i = 0; i < arraySize; i++) {
			array[i] = sc.nextInt();
		}
		array=sort(array);
		System.out.println("Enter key:");
		int key = sc.nextInt();
		System.out.println(ternarySearch(array, key, 0, array.length));
	}

}
