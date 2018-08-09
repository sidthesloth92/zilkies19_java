package com.zilker.PossibleGame;

import java.util.Scanner;

public class IsPossible {
	static int isMovePossible(int arr[], int last, int leap) {
		int flagInFun1,flagInFun2;
		if (last + 1 < leap) {
			//System.out.print("Win Possible");
			return 1;
		}
		if (arr[last] == 1 && arr[last-1] ==1 && arr[last - leap + 1] == 1 && arr[last - leap] == 1) {
			//System.out.println("Win Not Possible");
			return 0;
		}
		if (arr[last-2] ==1 && arr[last - leap] == 1 && arr[last - leap-1] == 1) {
			return 0;
		}
		flagInFun1 = isMovePossible(arr, last - leap, leap);
		flagInFun2 = isMovePossible(arr, last - leap - 1, leap);
		if(flagInFun1 == 0 || flagInFun2 == 0) {
			return 0;
		}
		return 1;
	}

	public static void main(String[] args) {
		int numberOfInput, leap, i,flag;
		Scanner sc = new Scanner(System.in);
		numberOfInput = sc.nextInt();
		leap = sc.nextInt();
		int[] array = new int[numberOfInput];
		for (i = 0; i < numberOfInput; i++) {
			array[i] = sc.nextInt();
		}
		flag = isMovePossible(array, numberOfInput - 1, leap);
		if(flag==0) {
			System.out.println("Not Possible");
		}
		else System.out.println("Possible");
		sc.close();
	}

}
