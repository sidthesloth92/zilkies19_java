package com.zilker.expressioneval;

import java.util.Scanner;

public class ExpressionEval {
	static int evaluate(int a, int b, int n) {
		int sum = a * n;
		for (int i = 0; i < n; i++) {
			sum += (int) Math.pow(2, i) * b * (n - i);

		}
		return sum;

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter values of a, b and n:");
		int a = in.nextInt();
		int b = in.nextInt();
		int n = in.nextInt();
		System.out.println("The value of the expression is: " + evaluate(a, b, n));
	}
}