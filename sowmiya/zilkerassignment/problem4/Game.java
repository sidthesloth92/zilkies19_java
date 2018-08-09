package com.zilkerassignment.problem4;

import java.util.Scanner;

public class Game {
	public static int Visited[] = new int[100];

	public static boolean gameResult(int position, int finalPosition, int leap, int[] array) {
		if (Visited[position] == 1)
			return false;
		Visited[position] = 1;
		if (array[position] == 1)
			return false;
		if (position >= finalPosition)
			return true;
		if (position < 0)
			return false;
		return gameResult(position + 1, finalPosition, leap, array)
				|| gameResult(position + leap, finalPosition, leap, array);

	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int input[] = new int[1000];
		int size = scan.nextInt();
		int leap = scan.nextInt();
		boolean output = true;
		int counter1;
		for (int counter = 0; counter < size; counter++) {
			input[counter] = scan.nextInt();

		}
		/*
		 * for(counter1=0;counter1<size-1;) { if(input[counter1]==0) {
		 * if(input[counter1+leap]==0) { counter1=counter1+leap; } else
		 * if(input[counter1+1]==0) { counter1=counter1+1; } else { output=false; break;
		 * } }
		 * 
		 * }
		 */

		System.out.println(gameResult(0, size - 1, leap, input));

	}
}
