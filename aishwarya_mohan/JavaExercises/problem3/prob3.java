package com.zilkertech.problem3;

import java.util.Scanner;

public class prob3 {
	public static void main(String args[]) {
		Scanner in=new Scanner(System.in);
		int a=in.nextInt();
		int b=in.nextInt();
		int n=in.nextInt();
		
		int sumOutput=0;
		for(int i=0;i<n;i++) {
			int varSum=a;
			for(int j=0;j<=i;j++) {
				varSum+=b*(1<<j);
			}
			sumOutput+=varSum;
		}
		System.out.println(sumOutput);
	}
}
