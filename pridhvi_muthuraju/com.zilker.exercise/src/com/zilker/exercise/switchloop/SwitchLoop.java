package com.zilker.exercise.switchloop;

import java.util.Scanner;

public class SwitchLoop {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1.Integer\n2.Float\n3.char\n4.String\n");
			int choice = sc.nextInt();
			try {
			switch (choice) {
			case 1:
				System.out.println("Enter an integer: ");
				int integerNumber = sc.nextInt();
				System.out.println(integerNumber);
				break;
			case 2:
				System.out.println("Enter a float number: ");
				float floatNumber = sc.nextFloat();
				System.out.println(floatNumber);
				break;
			case 3:
				System.out.println("Enter a character: ");
				String character = sc.next();
				if (character.length() == 1) {
					System.out.println(character);
				} else {
					System.out.println("Invalid input!!");
				}
				break;
			case 4:
				System.out.println("Enter a String: ");
				String string = sc.nextLine();
				System.out.println(string);
				break;
			}
			}catch(Exception e) {
				System.out.println("Invalid input! Try Again.");
			}
			System.out.println("Do you want to continue?(y/n)");
		} while (sc.next().equals("y"));
		//System.out.println("Thank You!");
	}
}
