package removeDuplicates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Source {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Program to Remove Duplicates");
		
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		
		ArrayList<String> list = new ArrayList<>(Arrays.asList(s.trim().split(" ")));
		
		int i;
		
		for(i=0;i<list.size();i++) {
			System.out.println(i+":"+list.get(i));
			//list.set(i, list.get(i).trim());
		}
		
		i=0;
		
		do {
			if(list.get(i).equals(list.get(i+1))) {
				list.remove(i+1);
			} else {
				i++;
			}
		}while(i < list.size()-1);
		
		for(i=0;i<list.size();i++) {
			System.out.print(list.get(i) + " ");
		}
	}

}
