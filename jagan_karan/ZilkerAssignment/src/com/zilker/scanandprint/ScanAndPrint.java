package com.zilker.scanandprint;
import java.util.Scanner;

public class ScanAndPrint {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in); 
				int option;
		do {
			System.out.println("OPTION\n1. Integer\n2. Float\n3. String\n4. Boolean\n5. Word\n6. Exit");
			option=sc.nextInt();
			switch(option)
			{
			case 1:System.out.println("Enter an Integer");
			       int integerValue=sc.nextInt();
			       System.out.println("Integer Value is "+integerValue);
			       break;
			case 2:System.out.println("Enter a Float Value");
		       	   float floatValue=sc.nextFloat();
		       	   System.out.println("Float Value is "+floatValue);
		       	   break;
			case 3:System.out.println("Enter a String");
					sc.nextLine();
				   String userString=sc.nextLine();
		           System.out.println("The String is "+userString);
		           break;
			case 4:System.out.println("Enter a Boolean");
     		       boolean booleanValue=sc.nextBoolean();
	     	       System.out.println("Boolean Value is "+booleanValue);
		           break;
			case 5:System.out.println("Enter a Word");
		       	   String word=sc.next();
		       	   System.out.println("Word is "+word);
		       	   break;
			case 6:System.out.println("Bye Bye");
					break;
			}
		}while(option!=6);
	}

}
