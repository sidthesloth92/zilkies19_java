import java.util.Scanner;
import java.util.logging.Logger;

import com.zilker.bean.LoginData;
import com.zilker.bean.RegisterData;
import com.zilker.constants.StringConstants;
import com.zilker.constants.OptionConstants;
import com.zilker.ui.HomePage;
import com.zilker.ui.Login;
import com.zilker.ui.Register;

public class ConferenceHallManagement {
	
	
	public static void main(String[] args)
	{
		System.setProperty("java.util.logging.SimpleFormatter.format","%5$s%6$s%n");
		
		HomePage homePage = new HomePage();
		
		homePage.HomeOptions();
		
	}
	
}
