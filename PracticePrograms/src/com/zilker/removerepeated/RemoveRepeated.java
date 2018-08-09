package com.zilker.removerepeated;
import java.util.*;
public class RemoveRepeated {
	public static void main(String args[]) {
		Scanner get=new Scanner(System.in);
		String s1=get.nextLine();
		String array[]=s1.split(" ");
		for(int index1=0;index1<array.length;index1++) {
			for(int index2=index1+1;index2<array.length;index2++) {
				if(array[index1].equals(array[index2]) || array[index2].contains(array[index1]) ||array[index2].contains(array[index1])) {
					if(array[index2].contains(array[index1])) {
						array[index1]="$";
					}
					else {
						array[index2]="$";	
					}
					
				}
			}
		}
		for(String x:array){
			if(!x.equals("$")) {
				System.out.print(x+" ");
			}
		}
	}
}
