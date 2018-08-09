package com.zilker.consecutivewords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConsecutiveWords {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(str.split("[!?.,\\s]")));
		// ArrayList<String> list = new ArrayList<String>(Arrays.asList(str)))
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).compareTo(list.get(i + 1)) == 0 /*&& list.get(i).compareTo(" ") != 0*/) {
				list.remove(i);
				i--;
			}

		}
		for (String s : list)
			System.out.print(s + " ");
	}
}
