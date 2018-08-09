package com.zilker.exercise.consecutive;

import java.util.Scanner;

public class ConsecutiveWords {
	private static Scanner sc;

	public static void main(String args[]) {
		sc = new Scanner(System.in);
		System.out.println("Enter a line");
		String s = sc.nextLine();
		String words[] = s.split(" ");
		for (int i = 0; i < words.length; i++) {
			if (words[i] != null) {
				//     Regex.Replace(words[i], @"[^0-9a-zA-Z]+", "")  
				for (int j = i + 1; j < words.length; j++) {
					if (words[i].equals(words[j])) {
						words[j] = null;
					}
				}
			}
		}
		for (int k = 0; k < words.length; k++) {
			if (words[k] != null) {
				System.out.print(words[k] + " ");
			}
		}
	}
}
