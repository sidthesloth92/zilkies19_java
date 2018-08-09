
import java.util.*;

public class ArrayGame {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the number of Elements ");
		
		int Size = in.nextInt();
		
		int values[] = new int[Size];
		
		System.out.println("Enter the Elements ");
		
		for (int i = 0; i < Size; i++) {
			
			values[i] = in.nextInt();
			
		}
		System.out.println("Enter the leap value");
		
		int leap = in.nextInt();
		
		if (leapCheck(values, leap, 1)) {
			
			System.out.println("YES");
			
		} else {
			
			System.out.println("NO");
			
		}
		in.close();
	}

	public static boolean leapCheck(int[] arrayPassed, int leapValue, int index) {
		
		System.out.println("the Array Passed Index" + arrayPassed[index]);
		
		System.out.println("The index value is " + index);
		
		if (index < 0 || arrayPassed[index] == 1) {
			
			return false;
			
		}
		if ((index == arrayPassed.length - 1) || index + leapValue > arrayPassed.length - 1) {
			
			return true;
			
		}
		return leapCheck(arrayPassed, leapValue, index + 1) || leapCheck(arrayPassed, leapValue, index - 1)
				
				|| leapCheck(arrayPassed, leapValue, index + leapValue);
	}
}
