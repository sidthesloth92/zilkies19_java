package com.zilkerassignment.problem3;

import java.util.Scanner;

public class Expression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		int a=scan.nextInt();
		int b=scan.nextInt();
		int n=scan.nextInt();
		System.out.println(a+b+n);
		int result=0,tempResult;
		tempResult=a;
		for(int counter=0;counter<n;counter++) {
			tempResult+=(int)Math.pow(2,counter)*b;
			result=result+tempResult;
			
		}
		System.out.print(result);
    //System.out.println(result);
	}

}
