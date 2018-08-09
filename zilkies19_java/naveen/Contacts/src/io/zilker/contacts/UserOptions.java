package io.zilker.contacts;

public class UserOptions {

	public static void printOptions() {
		System.out.println("----------------------------------------------------------");
		System.out.println("| 1- Create a contact                                    |");
		System.out.println("| 2- Edit a contact                                      |");
		System.out.println("| 3- Delete a contact                                    |");
		System.out.println("| 4- Sort by First Name                                  |");
		System.out.println("| 5- Sort by Last Name                                   |");
		System.out.println("| 6- Exit                                                |");
		System.out.println("----------------------------------------------------------");
		System.out.print("| Enter your choice (1/2/3/4/5/6)  :    ");
	}
	
	public static void showEditOptions() {
		System.out.println("----------------------------------------------------------");
		System.out.println("| 1- First Name                                          |");
		System.out.println("| 2- Last Name                                           |");
		System.out.println("| 3- Mobile Number                                       |");
		System.out.println("| 4- Home Number                                         |");
		System.out.println("| 5- Office Number                                       |");
		System.out.println("| 6- Email-ID                                            |");
		System.out.println("| 7- Finish Edit                                         |");
		System.out.println("----------------------------------------------------------");
	}

	public static void showEditOptionsUtil() {
		System.out.println("----------------------------------------------------------");
		System.out.println("| 1- Add                                                 |");
		System.out.println("| 2- Update                                              |");
		System.out.println("| 3- Delete                                              |");
		System.out.println("----------------------------------------------------------");
	}
	
}
