package io.ztech.placementportal.constants;

public class Regex {
	public static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{3,}$";
	public static final String PASSWORD_REGEX = "^[a-zA-z0-9]{3,}$";
	public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String REGISTER_NO_REGEX = "\\d+";
	public static final String STUDENT_NAME = "[a-zA-Z]+";
	public static final String DATE = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
	public static final String PHONE_NUMBER = "\\+\\d{2}-\\d{10}";
	public static final String BLOOD_GROUP = "(A|B|AB|O)[+-]";
	public static final String LOCATION = "^[A-Za-z]+";
	public static final String GENDER = "^M$|^F$";
	public static final String FLOAT = "\\d+(\\.\\d)?";
	public static final String MARKS = "^(0*100{1,1}\\.?((?<=\\.)0*)?%?$)|(^0*\\d{0,2}\\.?((?<=\\.)\\d*)?)$";
	public static final String CGPA = "^(10|[1-9])(\\.\\d{1,3})?$";
	public static final String ARREAR_COUNT = "^[0-9]$|^1[0-9]$|^2[0-9]$|^3[0-9]$|^4[0-5]$";
	public static final String NUMBER = "^\\d+$";
	public static final String CHOICE = "^Y$|^N$";
	public static final String DEPARTMENT = "^CSE$|^ECE$|^EEE$|^IT$|^MECH$|^EI$|^AUTO$";
	//"^((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})$";

}