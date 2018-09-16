
import io.ztech.autorate.ui.UIClass;

public class MainClass {

	public static void main(String[] args) {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");
		new UIClass().mainMenu();
	}

}
