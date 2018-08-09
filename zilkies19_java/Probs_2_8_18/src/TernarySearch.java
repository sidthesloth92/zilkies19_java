import java.util.*;

public class TernarySearch {

	static int search(int[] arr, int num, int start, int end) {
		if (num >= arr[start] && num <= arr[end]) {
			if (num == arr[start])
				return start;
			if (num == arr[end])
				return end;
			int first_block = start + (end - start + 1) / 3;
			int second_block = start + (end - start + 1) * 2 / 3;
			if (num >= arr[start] && num <= arr[first_block])
				return search(arr, num, start, first_block);
			if (num >= arr[first_block] && num <= arr[second_block])
				return search(arr, num, first_block, second_block);
			else
				return search(arr, num, second_block, end);
		}
		return -1;
	}

	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter the array size :  ");
			int n = scan.nextInt();
			int[] arr = new int[n];
			System.out.println("---  Enter array elements  ---");
			for (int i = 0; i < n; i++) {
				arr[i] = scan.nextInt();
			}
			System.out.print("Enter number to find : ");
			int find = scan.nextInt();
			System.out.println("Position is " + search(arr, find, 0, arr.length - 1));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
