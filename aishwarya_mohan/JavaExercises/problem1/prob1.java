package com.zilkertech.problem1;

import java.util.*;

public class prob1 {
	public static void main(String args[]) {
		char exitChoice = 'n';
		Scanner in = new Scanner(System.in);

		do {
			System.out.println("Choose an input type\n1.Int\t2.Character\t3.Float\t4.Boolean\t5.String");
			int choice = in.nextInt();
			try {
				switch (choice) {
				case 1:
					int var1 = in.nextInt();
					System.out.println(var1);
					break;
				case 2:
					String var2 = in.next();
					if (var2.length() > 1) {
						System.out.println("Enter a valid input");
					} else {
						System.out.println(var2);
					}
					break;
				case 3:
					float var3 = in.nextFloat();
					System.out.println(var3);
					break;
				case 4:
					boolean var4 = in.nextBoolean();
					System.out.println(var4);
					break;
				case 5:
					String var5= in.next();
					System.out.println(var5);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter a valid input");
			}
			System.out.println("Do you want to exit? (y/n)");
			exitChoice = in.next().charAt(0);
		} while (exitChoice == 'n');
		in.close();
	}

}
