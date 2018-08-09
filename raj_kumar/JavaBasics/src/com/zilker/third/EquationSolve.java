package com.zilker.third;

import java.util.Scanner;

public class EquationSolve {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the Value of A");
		int valueA = in.nextInt();
		System.out.println("Enter the Value of B");
		int valueB = in.nextInt();
		System.out.println("Enter the Value of N");
		int valueN = in.nextInt();
		EquationSolve objEquation = new EquationSolve();
		System.out.println(objEquation.computeEquation(valueA, valueB, valueN));
	}
	public int computeEquation(int aValue, int bValue, int nValue) {
		int sum = 0;
		for(int counter = 0;counter < nValue;counter++) {
			sum += aValue;
			for(int innerCount = 0; innerCount <= counter;innerCount++) {
				sum = (int) (sum + Math.pow(2, innerCount) * bValue);
			}
		}
		return sum;
	}
}
