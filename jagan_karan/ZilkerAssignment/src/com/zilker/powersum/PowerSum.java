package com.zilker.powersum;

import java.util.Scanner;

public class PowerSum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int value1, value2, sum = 0, totalsum = 0, number;
		System.out.println("Enter the Number of Times");
		number = sc.nextInt();
		System.out.println("Enter the Two Values ");
		value1 = sc.nextInt();
		value2 = sc.nextInt();
		for (int ctr = 0; ctr <= number - 1; ctr++) {
			if(ctr==0)
				{
				sum += (int) value1+(Math.pow(2, ctr) * value2);
				totalsum += sum;
				
				}
				else {
			sum += (int) (Math.pow(2, ctr) * value2);
			totalsum += sum;
			
			}
		}
		System.out.println(totalsum);
	}

}
