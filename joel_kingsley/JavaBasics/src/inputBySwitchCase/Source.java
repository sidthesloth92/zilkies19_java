package inputBySwitchCase;

import java.util.*;

public class Source {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		System.out.println("Program to Input and Print by Switch");
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("Menu");
			System.out.println("1. Integer");
			System.out.println("2. Character");
			System.out.println("3. String");
			System.out.println("4. Float");
			System.out.println("5. Double");
			System.out.println("6. Long");
			System.out.println("7. Byte");
			System.out.println("0. Exit");
			n = sc.nextInt();
			sc.nextLine();
			
			switch(n) {
				case 1:
				try {
					Integer a = sc.nextInt();
					System.out.println(a);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Is not a valid integer");
				}
					break;
				case 2:
					String b = sc.nextLine();
					
					if(b.length() <= 1) {
						System.out.println(b);	
					} else {
						System.out.println("False input");
					}
					break;
				case 3:
				try {
					String c = sc.nextLine();
					System.out.println(c);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Is not a string");
				}
					break;
				case 4:
				try {
					Float d = sc.nextFloat();
					System.out.println(d);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Is not a valid float");
				}
					break;
				case 5:
				try {
					Double e = sc.nextDouble();
					System.out.println(e);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Is not a valid double");
				}
					break;
				case 6:
				try {
					Long f = sc.nextLong();
					System.out.println(f);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Is not a valid long");
				}
					break;
				case 7:
				try {
					Byte g = sc.nextByte();
					System.out.println(g);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Is not a valid byte/out of range");
				}
					break;
				case 0:
					sc.close();
					return;
			}
			System.out.println("------------");
		} while(true);
	}

}