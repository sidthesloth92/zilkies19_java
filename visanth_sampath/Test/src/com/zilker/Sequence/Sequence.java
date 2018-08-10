package com.zilker.Sequence;

import java.util.Scanner;

public class Sequence {
	public static void main(String []args) {
		int a,b,n,totalSum=1,i;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the values of a,b and n");
		a= sc.nextInt();
		b= sc.nextInt();
		n= sc.nextInt();
		int []bValue = new int[n];
		bValue[0]= 1;
		for(i=1;i<n ;i++) {
			bValue[i]=  (int) (bValue[i-1]+ Math.pow(2,i));
			//System.out.print(bValue[i]+" ");
			totalSum = totalSum + bValue[i];
		}
		totalSum = totalSum + (a*n);
		System.out.print(totalSum);
	}

}
