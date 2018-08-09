import java.util.*;

public class PrintType {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char choice = ' ';
		while (choice != 'q') {
			System.out.println("---  ENTER A CHOICE  ---");
			System.out.print("Int(i) Float(f) Boolean(b) Char(c) Quit(q):  ");
			choice = scan.next().charAt(0);
			try {
				switch (choice) {
				case 'i':
					int num;
					System.out.print("Enter a number : ");
					num = scan.nextInt();
					System.out.println("Your number is " + num);
					break;
				case 'f':
					float float_num;
					System.out.print("Enter a float : ");
					float_num = scan.nextFloat();
					System.out.println("Your float number is " + float_num);
					break;
				case 'b':
					boolean bool;
					System.out.print("Enter true or false : ");
					bool = scan.nextBoolean();
					System.out.println("Your boolean value is " + bool);
					break;
				case 'c':
					char ch;
					System.out.print("Enter a character : ");
					ch = scan.next().charAt(0);
					System.out.println("Your character is " + ch);
					break;
				case 'q':
					System.out.println("Quitting");
					break;
				default:
					System.out.println("Invalid choice");
				}
			} catch (InputMismatchException e) {
				System.out.println("Oops -- Wrong format\n");
			} catch (Exception e) {
				System.out.println(e);
			}
			String temp = scan.nextLine();
		}
	}
}
