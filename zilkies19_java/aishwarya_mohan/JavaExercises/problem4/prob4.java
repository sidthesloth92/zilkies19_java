package com.zilkertech.problem4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob4 {
	
	
	public static void main(String args[]) {
		Scanner in=new Scanner(System.in);
		int noOfElements=in.nextInt();
		int inputArray[]= new int[noOfElements];
		int leap=in.nextInt();
		for(int i=0;i<noOfElements;i++) {
			inputArray[i]=in.nextInt();
		}
		
		boolean flag=false;
		
		Queue<Integer> queue=new LinkedList<Integer>();
		if(inputArray[0]==1) {
			System.out.println("Not possible");
			System.exit(0);
		}
		queue.add(inputArray[0]);
		
		int index=0;
		boolean visited[]=new boolean[inputArray.length];
		while(!queue.isEmpty()) {
			index=queue.poll();
			System.out.println(index);
			if(index>=inputArray.length-1) {
				flag=true;
				break;
			}
			if(index+1<inputArray.length && !visited[index+1] && inputArray[index+1]==0) {
				visited[index+1]=true;
				queue.add(index+1);
			}
			if(index+leap<inputArray.length && !visited[index+leap] && inputArray[index+leap]==0) {
				visited[index+leap]=true;
				queue.add(index+leap);
			}
			if(index!=0 && !visited[index-1] && inputArray[index-1]==0) {
				visited[index-1]=true;
				queue.add(index-1);
			}
		}
		
		if(flag) {
			System.out.println("Possible");
		}else {
			System.out.println("Not possible");
		}
	}
}

/*
for(int i=0;i<noOfElements;i++) {
if(inputArray[i]!=0) {
	if(i==0) {
		System.out.println("No");
		break;
	}else {
		
	}
}
}
*/

/*
static boolean ifPossible(int arr[], int index, int leap) {
if(index>=arr.length) {
	return true;
}

if(arr[index]==0) {
	return ifPossible(arr, index+leap, leap);
}
return ifPossible(arr, index+1, leap);
}
*/