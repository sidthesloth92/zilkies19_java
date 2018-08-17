package com.zilkerassignment.problem2;

import java.util.Scanner;

public class RemoveConsecutiveWords {

	public static void main(String args[]) {
		Scanner scan=new Scanner(System.in);
		String inputSentence;
		String sentence="";
		int counter=0,counter1;
		System.out.println("Enter the string");
		inputSentence=scan.nextLine();
		String words[]=inputSentence.split(" ");
		counter=words.length;
		for(counter1=0;counter1<counter-1;counter1++) {
			if(!words[counter1].equals(words[counter1+1])) {
				sentence=sentence+words[counter1]+" ";
			}
			else {
				sentence=sentence+words[counter1]+" ";
				int tempCount=0;
				for(int counter2=counter1+1;counter2<counter;counter2++) {
					if(words[counter1].equals(words[counter2])) {
						tempCount++;
					}
					else {
						break;
					}
				}
				counter1=counter1+tempCount;
			}
		}
		if(!words[counter-1].equals(words[counter-2])) {
			sentence=sentence+words[counter-1];
		}
		System.out.println(sentence);
}
}
