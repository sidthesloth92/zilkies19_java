package ternarysearch;
import java.util.*;
public class TernarySearch {
	public static boolean sort(int array[],int l,int r,int key) {
		if(r>=l) {
			int mid1=l+(r-l)/3;
			int mid2=r-(r-l)/3;
			if(array[mid1]==key) {
				return true;
			}
			if(array[mid1]==key) {
				return true;
			}
			if(array[mid1]>key) {
				return sort(array,l,mid1-1,key);
			}
			if(array[mid1]<key) {
				return sort(array,mid1+1,r,key);
			}
			return sort(array,mid1+1,mid2-1,key);
		}
		return false;
		
	}
	public static void main(String args[]) {
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int[] array=new int[n];
		for(int index=0;index<n;index++) {
			array[index]=in.nextInt();
		}
		int key=in.nextInt();
		int l=0,r=n-1;
		if(sort(array,l,r,key)) {
			System.out.println("Found");
		}
		else {
			System.out.println("Not Found");	
		}
		
		
	}
}
