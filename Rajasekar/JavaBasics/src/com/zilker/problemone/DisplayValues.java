package com.zilker.problemone;

import java.util.*;

public class DisplayValues {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		int ivalue;float fvalue;double dvalue;String cvalue;Boolean bvalue;
		do {
			System.out.printf("Enter your choice\n1.Integer 2.Float\n3.Double\n4.char\n5.boolean\n6.Exit");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter an Integer value");
				ivalue = input.nextInt();
				System.out.println("The Integer value is: " + ivalue);
				break;
			case 2:
				System.out.println("Enter a Floating value");
				fvalue = input.nextFloat();
				System.out.printf("The Floating value is: %f\n", fvalue);
				break;
			case 3:
				System.out.println("Enter a Double value");
				dvalue = input.nextDouble();
				System.out.println("The Double value is:" + dvalue);
				break;
			case 4:
				System.out.println("Enter a Character");
				cvalue = input.next();
				if(cvalue.length()==1) {
					System.out.println("The Character is:"+cvalue);
				}
				else {
					System.out.println("Invalid Character Entry");
				}
				break;
			case 5:
				System.out.println("Enter a Boolean value");
				bvalue = input.nextBoolean();
				System.out.println("The boolean is: " + bvalue);
				break;
			case 6:
				System.out.println("---Terminating the Program---");
				System.exit(0);
			}
		} while (true);
	}
}
