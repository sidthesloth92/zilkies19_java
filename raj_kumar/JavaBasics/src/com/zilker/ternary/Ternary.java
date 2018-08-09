package com.zilker.ternary;

import java.util.Arrays;
import java.util.Scanner;

public class Ternary {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of elements in the array !");
		int arraySize = in.nextInt();
		int[] array = new int[arraySize];
		for(int counter = 0;counter < arraySize;counter++) {
			array[counter] = in.nextInt();
		}
		Arrays.sort(array);
		System.out.println("Enter the value to search !");
		int valueToSearch = in.nextInt();
		Ternary objTernary = new Ternary();
		//System.out.println(objTernary.Search(0, arraySize - 1, valueToSearch, array));
		if(objTernary.Search(0, arraySize - 1, valueToSearch, array) == -1) {
			System.out.println("Not Found");
		}else {
			System.out.println("Found at index "+objTernary.Search(0, arraySize - 1, valueToSearch, array)+" Value is "+array[objTernary.Search(0, arraySize - 1, valueToSearch, array)]);
		}
	}
	public int Search(int leftEnd, int rightEnd, int value, int[] arrayPassed) {
		System.out.println(arrayPassed[rightEnd]);
		System.out.println(arrayPassed[leftEnd]);
		System.out.println(value);
		if(rightEnd >= leftEnd) {
			System.out.println(leftEnd+"  "+ rightEnd);;
			int midOne = leftEnd + (rightEnd - leftEnd) / 3;
			int midTwo = leftEnd + 2*(rightEnd - leftEnd) / 3;
			if(arrayPassed[leftEnd] == value) {
				return leftEnd;
			}
			if(arrayPassed[rightEnd] == value) {
				
				return rightEnd;
			}
			if(value < arrayPassed[leftEnd]) {
				return Search(leftEnd,midOne - 1, value, arrayPassed);
			}else if(value > arrayPassed[rightEnd]) {
				return Search(midTwo + 1, midTwo, value, arrayPassed);
			}else {
				return Search(midOne, midTwo, value, arrayPassed);
			}
		}else {
			return -1;
		}
		
		
		
	}
}
