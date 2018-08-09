package com.zilker.second;

import java.util.Scanner;

public class ConsecutiveWords {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the line you want to check for consecutive words !");
		String line = in.nextLine();
		ConsecutiveWords objConsecutiveWords = new ConsecutiveWords();
		String obtainedArray[] = objConsecutiveWords.removeConsecutive(line);
		for(int counter = 0;counter < obtainedArray.length;counter++) {
			System.out.print(obtainedArray[counter]+" ");
		}
		in.close();
	}
	public String[] removeConsecutive(String givenLine) {
		String wordArray[] = givenLine.split(" ");
		for(int pointerOne = 0;pointerOne < wordArray.length;pointerOne++) {
			for(int pointerTwo = 0;pointerTwo < wordArray.length;pointerTwo++) {
				if(pointerOne != pointerTwo) {
					if(wordArray[pointerOne].equals( wordArray[pointerTwo])) {
						wordArray[pointerTwo] = "";
					}
				}
			}
		}
		return wordArray;
	}
}
