import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {
	public static String[] fileRead(String count[], String name) throws IOException {
		String s = "";
		FileReader fr = new FileReader("/home/rajasekar/Desktop/" + name + ".txt");
		int i;
		while ((i = fr.read()) != -1) {
			s += (char) i;
		}
		count = s.split("\\s+");
		return count;
	}
}
