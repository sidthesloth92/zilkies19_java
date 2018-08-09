package com.zilker.ConsecutiveWords;

import java.util.Scanner;
import java.util.*;
public class ConsecutiveWords {
	public static void main(String [] args) {
		int totalWords,i;
		String givenLine,newLine;
		Scanner sc = new Scanner(System.in);
		givenLine = sc.nextLine();
		String []splittedWords = givenLine.split(" ");
		totalWords = splittedWords.length;
		newLine = "";
		for(i=0 ;i<totalWords ;i++) {
			newLine =newLine + splittedWords[i]+" ";
			while(i+1<totalWords && splittedWords[i].equals(splittedWords[i+1])) {
				i++;
			}
		}
		System.out.println(newLine);
		
	}
}