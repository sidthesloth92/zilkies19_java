import java.util.Scanner;

public class TernarySearchProblem {

	public static void main(String[] args) {

		int nvalue, key, left, right;

		int[] values = new int[100];

		Scanner in = new Scanner(System.in);

		nvalue = in.nextInt();

		for (int pos = 0; pos < nvalue; pos++) {

			values[pos] = in.nextInt();

		}
		for (int itr = 0; itr < nvalue - 1; itr++) {
			for (int pos = itr + 1; pos < nvalue; pos++) {
				if (values[itr] < values[pos]) {
					int temp = values[pos];
					values[pos] = values[itr];
					values[itr] = temp;
				}

			}
		}
		
		left = 0;
		right = nvalue;

		key = in.nextInt();

		if (TernarySearch(key, values, left, right) == 1) {

			System.out.println("Success");

		} else {

			System.out.println("Failed");

		}

	}

	static int TernarySearch(int key, int[] values, int left, int right) {

		if (right >= left) {

			int mid1 = left + (right - left) / 3;

			int mid2 = right - (right - left) / 3;

			if (values[mid1] == key) {

				return 1;
			}
			if (values[mid2] == key) {
				return 1;
			}

			if (key < values[mid1]) {
				return TernarySearch(key, values, left, mid1 + 1);
			} else if (key > values[mid2]) {
				return TernarySearch(key, values, mid2 + 1, right);
			} else{
				return TernarySearch(key, values, mid1 + 1, mid2 - 1);
			}
			
		}

		return 0;
	}

}
