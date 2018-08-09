package com.zilker.getinputbyswitch;
import java.util.*;
public class GetInputBySwitch {
	public static void main(String args[]) {
		Scanner get=new Scanner(System.in);
		int flag=0;
		do {
			System.out.println("Select the datatype");
			System.out.println("1.char 2.int 3.float 4.short 5.byte 6.string 7.boolean 8.exit");
			int choice=get.nextInt();get.nextLine();
			
			switch(choice) {
			case 1:	System.out.println("Enter character");
					String input1=get.next();
					if(input1.length()==1) {
						System.out.println("Entered character is "+input1);
					}
					else {
						System.out.println("False Input");
					}
					break;
			case 2:	System.out.println("Enter Integer");
					try {
						int input2=Integer.parseInt(get.next());
						System.out.println("Entered Integer is "+input2);
					}
					catch(IllegalArgumentException  ex) {
						System.out.println("False Input");
					}
					break;
			case 3:	System.out.println("Enter float");
					try {
						double input3=Double.parseDouble(get.next());
						System.out.println("Entered float value is "+input3);
					}
					catch(NumberFormatException ex) {
						System.out.println("False Input");
					}
					break;
			case 4:	System.out.println("Enter short");
					try {
						short input4=Short.parseShort(get.next());
						System.out.println("Entered Short value is "+input4);
					}
					catch(NumberFormatException ex) {
						System.out.println("False Input");
					}
					break;
			case 5:	System.out.println("Enter Byte");
					try {
						byte input5=Byte.parseByte(get.next());
						System.out.println("Entered Byte value is "+input5);
					}
					catch(NumberFormatException ex) {
						System.out.println("False Input");
					}
					break;
			case 6:	System.out.println("Enter String");
					try {
						
						String input6=get.nextLine();
						System.out.println("Entered String is "+input6);
					}
					catch(NumberFormatException ex) {
						System.out.println("False Input");
					}
					break;
			case 7:	System.out.println("Enter Boolean");
						String bool=get.next();
						if(bool.equals("true") ||bool.equals("false") ||bool.equals("TRUE")||bool.equals("FALSE")) {
							System.out.println("Entered boolean value is "+bool);
						}
						else {
							System.out.println("False Input");
						}
					break;
			case 8: flag=1;
					break;
			default: System.out.println("Enter valid option");  
			}
		}while(flag!=1);
		
	}

}
