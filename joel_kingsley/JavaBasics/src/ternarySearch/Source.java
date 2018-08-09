package ternarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Source {

	static int performTernarySearch(ArrayList<Integer> list, int start, int end, int elementToSearch) {
		
		
		int mid1 = start + (end-start)/3;
		int mid2 = end - (end-start)/3;
		
		System.out.format("start:%d end:%d mid1:%d mid2:%d\n",start,end,mid1,mid2);
		
		if(start >= end && list.get(start) != elementToSearch) {
			return -1;
		}
		if(elementToSearch == list.get(mid1)) {
			return mid1;
		} else if(elementToSearch == list.get(mid2)) {
			return mid2;
		} else if(elementToSearch < list.get(mid1)) {
			return performTernarySearch(list,start,mid1-1,elementToSearch);
		} else if(elementToSearch > list.get(mid2)) {
			return performTernarySearch(list,mid2+1,end,elementToSearch);
		} else if(elementToSearch > list.get(mid1) && elementToSearch < list.get(mid2)) {
			return performTernarySearch(list,mid1+1,mid2-1,elementToSearch);
		}
		return -2;
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 0;
		Scanner sc = new Scanner(System.in);
	
		
		try {
			System.out.print("Enter n:");
			n = sc.nextInt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0;i<n;i++) {
			try {
				list.add(sc.nextInt());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int elementToSearch = sc.nextInt();
		Collections.sort(list);
		
		System.out.println("Sorted list:");
		for(int i=0;i<n;i++) {
			list.get(i);
		}
		
		int locatedAt = performTernarySearch(list,0,list.size()-1,elementToSearch);
		System.out.println("Element present at:" + locatedAt);
	}

}
