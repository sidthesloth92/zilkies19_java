package io.ztech.contactapp.constants;

//=========================================================================================================================================
// REGEX CONSTANT INTERFACE - REGULAR EXPRESSION CONSTANT STRINGS
//=========================================================================================================================================
public interface RegexConstants {
	public static final String numRegex = "\\d+";
	public static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
			+ "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
}
