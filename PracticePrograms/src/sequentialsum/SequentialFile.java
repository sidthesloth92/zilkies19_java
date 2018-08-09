package sequentialsum;
import java.util.*;
public class SequentialFile {
	public static void main(String args[]) {
		Scanner get=new Scanner(System.in);
		try {
			int limit=Integer.parseInt(get.next());
			int a=Integer.parseInt(get.next());
			int b=Integer.parseInt(get.next());
			int sum=0;
			sum+=(a+b);
			for(int i=1;i<limit;i++) {
				int temp=(int) (sum+(a+Math.pow(2, i)*b));
				sum+=temp;
			}
			System.out.println(sum);
		}
		catch(NumberFormatException ex) {
			System.out.println("False Input");
		}
		
	}
}
