package com.zilker.problemthree;

import java.util.Scanner;

public class SeriesSum {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the values for n,a,b respectively");
		int n = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		int finalSum = a + b;
		int lastSum = finalSum;
		int i = 1;
		while (i < n) {
			int res = ((int) Math.pow(2, i)) * b;
			finalSum = finalSum + res;
			lastSum += finalSum;
			i++;
		}
		System.out.println(lastSum);
	}
}
