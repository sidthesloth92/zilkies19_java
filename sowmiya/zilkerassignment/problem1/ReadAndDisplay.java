package com.zilkerassignment.problem1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReadAndDisplay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int selectOption;
		try {
			do {
				System.out.println("Enter the datatype of the variable...");
				System.out.println("Select");
				System.out.println("1-Integer\n2-float\n3-double\n4-boolean\n5-character\n6-Exit");
				selectOption = scan.nextInt();

				switch (selectOption) {
				case 1:
					int number = scan.nextInt();
					System.out.println(number);
					break;
				case 2:
					float floatNumber = scan.nextFloat();
					System.out.println(floatNumber);
					break;
				case 3:
					double doubleNumber = scan.nextDouble();
					System.out.println(doubleNumber);
					break;
				case 4:
					boolean boolVal = scan.nextBoolean();
					System.out.println(boolVal);
					break;
				case 5:
					scan.nextLine();
					String charVal = scan.nextLine();
					if (charVal.length() == 1)
						System.out.println(charVal);
					else
						System.out.println("enter a character..");
					break;
				case 6:
					String input = scan.nextLine();
					System.out.println(input);
					break;
				case 7:
					break;
				}

			} while (selectOption != 6);
		} catch (InputMismatchException e) {
			System.out.println("Enter the valid input");

		}

	}
}
