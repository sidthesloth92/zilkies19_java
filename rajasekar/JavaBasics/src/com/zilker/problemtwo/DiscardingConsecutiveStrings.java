package com.zilker.problemtwo;
import java.util.*;
public class DiscardingConsecutiveStrings {
	public static void main(String args[]) {
		Scanner in=new Scanner(System.in);
		System.out.println("Enter a String");
		String text=in.nextLine();
		String arr[]=text.split("\\s+");
		int i=0;
		while(i<arr.length-1) {
			int f=0;
			int k=i+1;
			while(arr[i].equals(arr[k])) {
				arr[k]="";
				k++;
				if(k>=arr.length-1)
					break;
			}
			i++;
		}
		if(arr[0].equals(arr[arr.length-1])) {
			arr[arr.length-1]="";
		}
		text="";
		for(int j=0;j<arr.length;j++) {
			if(arr[j].equals("")) {
				continue;
			}
			else {
				text+=arr[j];
				text+=" ";
			}
		}
		System.out.println(text);
	}
}