package com.zilker.leapgame;
import java.util.*;
public class LeapGame {
	public static void main(String args[]) {
		Scanner get=new Scanner(System.in);
		int n=get.nextInt();
		int leapCount=get.nextInt();
		int[] array=new int[n];
		for(int index=0;index<n;index++) {
			array[index]=get.nextInt();
		}
		int currentIndex=0;
		while(true) {
			if(currentIndex+leapCount>=n  || currentIndex+1>=n ) {
				System.out.println("TRUE");
				break;
			}
			else if(array[currentIndex]+leapCount==0) {
				currentIndex=currentIndex+leapCount;
			}
			else if(array[currentIndex+1]==0) {
				currentIndex++;
			}
			else {
				System.out.println("FALSE");break;
			}
		}
	}
}
