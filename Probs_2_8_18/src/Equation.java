import java.util.*;

public class Equation {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter n, a, b :  ");
		int n, a, b;
		try {
			n = scan.nextInt();
			a = scan.nextInt();
			b = scan.nextInt();
			int sum = n * a;
			for (int i = 0; i < n; i++)
				sum += (n - i) * (int) Math.pow(2, i) * b;
			System.out.println("sum is " + sum);
		} catch (Exception e) {
			System.out.println("Invalid number");
		}
		
	}
}
