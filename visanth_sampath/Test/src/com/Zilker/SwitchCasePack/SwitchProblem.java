package com.Zilker.SwitchCasePack;

import java.util.Scanner;

public class SwitchProblem {
	public static void main(String [] args) {
		int choice , numberInInt;
		float numberInFloat;
		String line;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Enter Your Choice:\n1.Integer\n2.Float\n3.String\n4.exit");
			choice = sc.nextInt();
			switch(choice) {
			case 1: 
				numberInInt = sc.nextInt();
				System.out.println(numberInInt);
				break;
			case 2:
				numberInFloat = sc.nextFloat();
				System.out.println(numberInFloat);
				break;
			case 3:
				line = sc.nextLine();
				System.out.println(line);
				break;
			case 4: break;
			default : 
				System.out.println("Invalid Input");
				break;
			}
		}while(choice!=4);
	}

}
