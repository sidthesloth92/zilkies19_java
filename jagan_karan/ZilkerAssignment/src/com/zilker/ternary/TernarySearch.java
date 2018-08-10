package com.zilker.ternary;
import java.util.Scanner;
public class TernarySearch {
	public static int ternaryRecursive(int left,int right,int search,int[] num)
	{
		//System.out.println(left+" "+right);
		if(right>=left) {
		int mid1=left+(right-left)/3;
		int mid2=right-(right-left)/3;
		if(search==num[mid1])
			return 1;
		else if(search==num[mid2])
			return 1;
		else if(search>num[mid1])
			return ternaryRecursive(mid2+1, right, search, num);
		else if(search<num[mid2])
			return ternaryRecursive(left, mid1-1, search, num);
		else
			return ternaryRecursive(mid1+1, mid2-1, search, num);
		}
		else
			return -1;
		
	}
	
	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	int[] numbers = new int[100];
	System.out.println("Enter the Number of Values");
	int totalNumber=sc.nextInt();
	System.out.println("Enter the Values");
	for(int ctr=0;ctr<totalNumber;ctr++)
		numbers[ctr]=sc.nextInt();
	for(int ptr=0;ptr<totalNumber-1;ptr++)
	{
		for(int ctr=0;ctr<totalNumber-ptr-1;ctr++)
		{
			if(numbers[ctr]>numbers[ctr+1])
			{
				int temp;
				temp=numbers[ctr+1];
				numbers[ctr+1]=numbers[ctr];
				numbers[ctr]=temp;
			}
		}
	}
	System.out.println("Enter the Search value");
	int searchValue=sc.nextInt();
	int result=ternaryRecursive(0, totalNumber, searchValue,numbers);
	if(result==1)
		System.out.println("PRESENT");
	else if(result==-1)
		System.out.println("NOT PRESENT");
}
	
}