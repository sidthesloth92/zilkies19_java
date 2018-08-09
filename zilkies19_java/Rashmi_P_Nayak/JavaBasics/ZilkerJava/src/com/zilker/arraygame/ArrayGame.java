package com.zilker.arraygame;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ArrayGame {
	static boolean canWin(int[] arr, int n, int leap) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[arr.length];
		q.add(0);
		boolean flag = false;
		while (!q.isEmpty()) {
			int pos = q.remove();
			if (pos + 1 >= n - 1 || pos + leap >= n - 1) {
				return true;
			}
			if (pos + 1 < n && arr[pos + 1] == 0 && !visited[pos + 1]) {
				q.add(pos + 1);
				visited[pos + 1] = true;
			}
			if (pos != 0 && arr[pos - 1] == 0 && !visited[pos - 1]) {
				q.add(pos - 1);
				visited[pos - 1] = true;
			}
			if (pos + leap < n && arr[pos + leap] == 0 && !visited[pos + leap]) {
				q.add(pos + leap);
				visited[pos + leap] = true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int leap = in.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = in.nextInt();
		System.out.println(canWin(arr, n, leap));
	}

}
