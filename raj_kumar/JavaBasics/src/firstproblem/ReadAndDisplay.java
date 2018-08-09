package firstproblem;
import java.util.Scanner;

public class ReadAndDisplay {
	Scanner in = new Scanner(System.in);
	public static void main(String args[]) {
		ReadAndDisplay objOfReadAndDisplay = new ReadAndDisplay();
		objOfReadAndDisplay.toReadAndDisplay();
	}
	public void toReadAndDisplay() {
		boolean conditionVar = true;
		do {
		System.out.println("Enter a Data Type you wish to Enter");
		System.out.println("====================================");
		System.out.println("Enter 1 for Int");
		System.out.println("Enter 2 for String");
		System.out.println("Enter 3 for Char");
		System.out.println("Enter 4 to Exit");
		System.out.println("Enter 5 for Boolean");
		System.out.println("====================================");
		int option = in.nextInt();
		switch(option) {
		 case 1:
			 int value;
			 System.out.println("Enter the Integer Value");
			 try {
				 //https://stackoverflow.com/questions/19925047/how-to-check-the-input-is-an-integer-or-not-in-java
				 value = Integer.parseInt(in.next());
				 System.out.println("You have entered the value "+value);
			 }catch(NumberFormatException e) {
				 System.out.println("Enter A Valid Data Type");
			 }
			 break;
		 case 2:
			 System.out.println("Enter the String Value");
			 String stringValue = in.next();
			 System.out.println("You have entered the value "+stringValue);
			 break;
		 case 3:
			 System.out.println("Enter the Character Value");
			 String charValue = in.next();
			 if(charValue.length() == 1) {
				 System.out.println("You have entered the value "+charValue);
			 }else {
				 System.out.println("Enter a Valid Data");
			 }
			 break;
		 case 4:
			 conditionVar = false;
			 break;
		 case 5:
			 System.out.println("Enter a Boolean Value");
			 String boolStringValue = in.next();
			 if(boolStringValue != "true" && boolStringValue != "false") {
				 System.out.println("Please Enter a Boolean Value");
			 }else {
				 boolean bool = Boolean.parseBoolean(boolStringValue);
				 System.out.println("The Entered Boolean Value is "+bool);
			 }
			 break;
		}
		
		}while(conditionVar);
		System.out.println("You have exited the main menu !");
	}
}
