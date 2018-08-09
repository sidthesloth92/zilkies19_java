package com.zilkertech.problem2;

import java.util.Scanner;

public class prob2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String stringInput = in.nextLine();

		String inputArray[] = stringInput.split(" ");
		StringBuilder stringOutput = new StringBuilder();
		for (int i = 0; i < inputArray.length; i++) {
			if (i == inputArray.length - 1) {
				stringOutput.append(inputArray[i]);
			} else {
				if (!inputArray[i].equals(inputArray[i + 1])) {
					stringOutput.append(inputArray[i] + " ");
				}
			}

		}

		System.out.println(stringOutput);
		/*
		 * StringBuilder stringOutput = new StringBuilder();
		 * 
		 * StringBuilder string1 = new StringBuilder(); StringBuilder string2 = new
		 * StringBuilder();
		 * 
		 * for (int i = 0; i < stringInput.length(); i++) { char
		 * c=stringInput.charAt(i); if(c>='a' && c<='z' || c>='A' && c<='Z') {
		 * string2.append(stringInput.charAt(i));
		 * 
		 * }else { if(string1!=string2) { stringOutput.append(string2); string1=string2;
		 * string2=new StringBuilder(); } stringOutput.append(stringInput.charAt(i)); }
		 * }
		 * 
		 * 
		 * 
		 * stringOutput.append(string2); System.out.println(stringOutput);
		 */

		in.close();
	}
}

//  hi $$ .. a  b

/*
 * String inputArray[]=stringInput.split(" "); StringBuilder stringOutput=new
 * StringBuilder(); for(int i=0;i<inputArray.length;i++) {
 * if(i==inputArray.length-1) { stringOutput.append(inputArray[i]); }else {
 * if(!inputArray[i].equals(inputArray[i+1])) {
 * stringOutput.append(inputArray[i]+" "); } }
 * 
 * }
 * 
 * System.out.println(stringOutput);
 */