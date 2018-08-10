package com.zilker.removerecursive;

import java.util.Scanner;

public class RemoveRecursive {

	public static void main(String[] args) {

		System.out.println("Enter a String");
		Scanner sc = new Scanner(System.in);
		String userLine = sc.nextLine();
		String[] words = userLine.split(" ");
		for (int ctr = 0; ctr < words.length; ctr++) {
			for (int ptr = 0; ptr < words.length; ptr++) {
				if (words[ctr].equals(words[ptr]) || words[ctr].contains(words[ptr])) {
					if (ctr != ptr) {
						words[ptr] = "";
					}
				}
			}

		}
		for (int ctr = 0; ctr < words.length; ctr++) {
			System.out.print(words[ctr] + " ");
		}
	}
}
