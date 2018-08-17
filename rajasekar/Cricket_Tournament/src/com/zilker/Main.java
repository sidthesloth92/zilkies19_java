

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import com.zilker.constant.ConsoleStrings;
import com.zilker.ui.DisplayMenu;
import com.zilker.ui.FetchAndDisplay;
import com.zilker.dao.DaoImplementation;

public class Main {
	public static void main(String args[]) throws IOException {
		new DisplayMenu().showOption();
	}
}