import java.util.Scanner;

public class RemoveConsecutive {

	public static void main(String[] args) {
		
		String line,newline="";
		
		int length;
		String firstword="",secondword="";
		
		Scanner in=new Scanner(System.in);
		
		line=in.nextLine();
		
		length=line.length();
		
		for(int pos=0;pos<=length;pos++) 
		{
			if( pos==length || line.charAt(pos)==' ' || line.charAt(pos)<65 && line.charAt(pos)>122) {
				//newline+=" ";
				if(firstword.equals(secondword)) {
					firstword="";
					//secondword="";
					//newline+=" ";
				}else {
					if(newline.equals("")) {
						newline+=firstword;
					}else {
					newline=newline+" "+firstword;
					}
					secondword=firstword;
					firstword="";
				}
			}else {
				firstword+=line.charAt(pos);
			}
		}
		System.out.println(newline);
	}
	
}
