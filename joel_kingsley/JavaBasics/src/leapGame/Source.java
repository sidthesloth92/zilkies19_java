package leapGame;

import java.util.ArrayList;
import java.util.Scanner;

public class Source {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int leap = sc.nextInt();
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			list.add(sc.nextInt());
		}

		int index = 0;
		while (index < n - 1 && index >= 0) {
			if(index + leap > n) {
				index += leap;
			} else if(index + 1 > n) {
				index += 1;
			}
			if (list.get(index + leap) == 0) {
				index += leap;
			} else if (list.get(index + 1) == 0) {
				index += 1;
			} else {
				index -= 1;
			}
		}
		if (index > n - 1) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
	}

}
