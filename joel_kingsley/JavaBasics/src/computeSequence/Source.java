package computeSequence;

import java.util.Scanner;

public class Source {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a,b,n;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a:");
		a = sc.nextInt();
		System.out.print("Enter b:");
		b = sc.nextInt();
		System.out.print("Enter n:");
		n = sc.nextInt();
		
		int result = a + b;
		int prevResult = result;
		
		for(int i=1;i<n;i++) {
			System.out.println("Intermediate:" + result);
			int temp = (int) (prevResult + Math.pow(2, i)*b); 
			result += temp;
			prevResult = temp;
		}
		
		System.out.println("Result:" + result);
		
		sc.close();
	}
}
	