package com.zilker.game;

import java.util.Scanner;

public class Game {

	public static int moveLeft(int[] stream, int leap, int ptr) {
		if (ptr + leap >= stream.length || ptr + 1 >= stream.length) {
			return 1;
		} else if (leap == 1) {
			if (stream[ptr + leap] == 0) {
				return moveLeft(stream, leap, ptr + leap);
			} else {
				if (ptr - 1 != -1 && stream[ptr - 1] != 1) {
					return moveLeft(stream, leap, ptr - 1);
				} else {
					return 0;
				}

			}
		} else {
			if (stream[ptr + leap] == 0) {
				return moveLeft(stream, leap, ptr + leap);
			} else if (stream[ptr + leap] == 1) {
				if (ptr - 1 != -1 && stream[ptr - 1] != 1) {
					return moveLeft(stream, leap, ptr - 1);
				} else {
					return 0;
				}
			} else if (stream[ptr + 1] == 0) {
				return moveLeft(stream, leap, ptr + 1);
			} else if (ptr - 1 != -1 && stream[ptr - 1] != 1) {
				return moveLeft(stream, leap, ptr - 1);
			} else {
				return 0;
			}
		}

}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// Enter the Size
		int limit = input.nextInt();
		// Enter the Leap
		int leap = input.nextInt();
		// Enter the Stream of Values
		int[] stream = new int[limit];
		for (int ptr = 0; ptr < limit; ptr++) {
			stream[ptr] = input.nextInt();
		}
		int result = moveLeft(stream, leap, 0);
		System.out.println(result);
		if (result == 1)
			System.out.println("Won");
		else
			System.out.println("Lose");
	}

}
