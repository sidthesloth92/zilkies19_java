package com.zilker.exercise.equation;

import java.util.Scanner;

public class Equation {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a: ");
		int a = sc.nextInt();
		System.out.print("Enter b: ");
		int b = sc.nextInt();
		System.out.print("Enter n: ");
		int n = sc.nextInt();
		int sum = 0, count = 0;
		for (int i = 0; i < n; i++) {
			sum += a;
			for (int j = 0; j <= count; j++)
				sum += (Math.pow(2, j) * b);
			count++;
		}
		System.out.println("Sum= " + sum);
	}
}
