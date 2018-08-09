package com.zilker.switchcase;

import java.util.Scanner;

public class SwitchCaseMenu {
	static void displayMenu() {
		Scanner in = new Scanner(System.in);
		System.out.println("1.Int 2.Float 3.Double 4.Char 5.Boolean 6.String\nEnter Data type:");
		int choice = in.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter integer:");
			try {
				int intNum = in.nextInt();
				System.out.println("Your integer is " + intNum);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case 2:
			System.out.println("Enter float:");
			try {
				float floatNum = in.nextFloat();
				System.out.println("Your float is " + floatNum);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case 3:
			System.out.println("Enter double:");
			try {
				double doubleNum = in.nextDouble();
				System.out.println("Your double is " + doubleNum);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case 4:
			System.out.println("Enter char:");
			try {
				String str = in.next();
				if (str.length() > 1)
					System.out.println("Input not a char!");
				else
					System.out.println("Your character is " + str.charAt(0));
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case 5:
			System.out.println("Enter boolean:");
			try {
				boolean bool = in.nextBoolean();
				System.out.println("Your boolean is " + bool);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case 6:
			System.out.println("Enter String:");
			try {
				in.nextLine();
				String str = in.nextLine();
				System.out.println("Your string is "+str);
			}
			catch(Exception e) {
				System.out.println(e);
			}
		default:
			System.out.println("Enter valid option!");
		}
	}

	public static void main(String[] args) {
		char choice;
		Scanner in = new Scanner(System.in);
		do {
			displayMenu();
			System.out.println("Do you want to repeat? y - yes n - no");
			choice = in.next().charAt(0);
		} while (choice == 'y');
	}
}
