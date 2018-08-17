import java.util.Scanner;

public class SwitchClass {
public static void main(String[] args) {
		
		int choice,intvalue;
		float floatvalue;
		String Stringvalue;
		Boolean bool;
		char charvalue;
		int dowhileflag=1;
		Scanner in=new Scanner(System.in);
		
		do {
			
			System.out.println("1.Integer\n2.Character\n3.Float\n4.String\n5.Boolean");
			
			choice=in.nextInt();
			
			switch(choice) {
			
			case 1:
				intvalue=in.nextInt();
				System.out.println(intvalue+"");
				break;
			case 2:
				charvalue=in.next().charAt(0);
				System.out.println(charvalue+"");
				break;
			case 3:
				floatvalue=in.nextFloat();
				System.out.println(floatvalue+"");
				break;
			case 4:
				Stringvalue=in.nextLine();
				System.out.println(Stringvalue+"");
				break;
			case 5:
				bool=in.nextBoolean();
				System.out.println(bool);
				break;
			default:
				dowhileflag=0;
			}
			
		}while(dowhileflag==1);
		
	}
}
