package com.zilkerassignment.problem5;

import java.util.Arrays;
import java.util.Scanner;

public class TernarySearch {
	public static  int  search(int first,int last,int element,int[] array) {
		if(last>=first) {
		int mid1=first+(last-first)/3;
		int mid2=last-(last-first)/3;
		if(element==array[mid1])
		{
			return mid1;
		}
		else if(element==array[mid2]) {
			return mid2;
		}
		else if(array[mid1]>element) {
			return search(first,mid1-1,element,array);
		}
		else if(array[mid2]<element) {
			return search(mid2+1,last,element,array);
		}
		else
			return search(mid1+1,mid2-1,element,array);
		}
	return -1;
	
}
	
   
	public static void main(String args[]) {
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		int array[]=new int[1000];
		int element;
		for(int i=0;i<n;i++) {
			array[i]=scan.nextInt();
		}
		element=scan.nextInt();
		sort(array,n);
	   for(int i=0;i<n;i++)
		   System.out.println(array[i]);
		System.out.println(element);
		if(search(0,n,element,array)!=-1)
		{
		System.out.println("Element Found At"+search(0,n-1,element,array));	
		}
		else {
			System.out.println("Element not found");
		}
		
	}


	private static void sort(int[] a, int n) {
		// TODO Auto-generated method stub
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				int temp=a[i];
				a[i]=a[j];
				a[j]=temp;
			}
		}
		
	}
}
