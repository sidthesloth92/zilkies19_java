import java.util.*;

public class RemoveDups {
	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);
			String str = scan.nextLine();
			String[] words = str.split(" ");
			for (int i = 0; i < words.length - 1; i++) {
				if (words[i].compareTo(words[i + 1]) != 0)
					System.out.print(words[i] + " ");
			}
			System.out.println(words[words.length - 1]);
		} catch (Exception e) {
			System.out.println("Invalid input");
		}
	}
}
