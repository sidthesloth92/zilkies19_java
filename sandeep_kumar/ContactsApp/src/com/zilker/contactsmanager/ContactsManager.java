package com.zilker.contactsmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;

import com.zilker.beans.Beans;
import com.zilker.sqlconstants.SqlConstants;
import com.zilker.utils.*;

public class ContactsManager extends Beans {
	public static Scanner in = new Scanner(System.in);
	public static final Logger logger = Logger.getLogger("ContactsManager.class");
	public static boolean proceed;
	public static String getInput;

	public String scanName() {
		proceed = true;
		do {
			System.out.println("Enter the Name");
			getInput = in.nextLine();
			if (Validator.validate(getInput, "[a-zA-Z]+")) {
				proceed = false;
			}
			else {
				System.err.println(SqlConstants.INVALID_INPUT);
			}
		} while (proceed);
		return getInput;
	}

	public String scanOfficeNumber() {
		getInput = in.next();
		if (getInput.equals("-1")) {
			return "terminate";
		}
		if (Validator.validate(getInput, "^\\+[0-9]{2,4}-[0-9]{3,4}-[0-9]{4,7}$")) {
			return getInput;
		}
		else {
			System.err.println(SqlConstants.INVALID_INPUT);
		}
		return "falseInput";
	}

	public ArrayList<String> scanOfficeList() {
		ArrayList<String> officeList = new ArrayList<String>();
		proceed = true;
		do {
			System.out.println("Enter the Office Number. Press -1 if over.");
			do {
				String getInput = scanOfficeNumber();
				if (getInput.equals("terminate")) {
					proceed = false;
					continue;
				} else if (!getInput.equals("falseInput")) {
					officeList.add(getInput);
				}
			} while (proceed);
		} while (proceed);
		return officeList;
	}

	public String scanHomeNumber() {
		getInput = in.next();
		if (getInput.equals("-1")) {
			return "terminate";
		}
		if (Validator.validate(getInput, "^\\+[0-9]{2,4}-[0-9]{3,4}-[0-9]{4,7}$")) {
			return getInput;
		}
		else {
			System.err.println(SqlConstants.INVALID_INPUT);
		}
		return "falseInput";
	}

	public ArrayList<String> scanHomeList() {
		ArrayList<String> homeList = new ArrayList<String>();
		proceed = true;
		do {
			System.out.println("Enter the Home Number. Press -1 if over.");
			do {
				String getInput = scanHomeNumber();
				if (getInput.equals("terminate")) {
					proceed = false;
					continue;
				} else if (!getInput.equals("falseInput")) {
					homeList.add(getInput);
				}
			} while (proceed);
		} while (proceed);
		return homeList;
	}

	public String scanMobileNumber() {
		getInput = in.next();
		if (getInput.equals("-1")) {
			return "terminate";
		}
		if (Validator.validate(getInput, "^\\+[0-9]{2,4}-[0-9]{10}")) {
			return getInput;
		}
		else {
			System.err.println(SqlConstants.INVALID_INPUT);
		}
		return "falseInput";
	}

	public ArrayList<String> scanMobileList() {
		ArrayList<String> mobileList = new ArrayList<String>();
		proceed = true;
		do {
			System.out.println("Enter the Mobile Number. Press -1 if over.");
			do {
				String getInput = scanMobileNumber();
				if (getInput.equals("terminate")) {
					proceed = false;
					continue;
				} else if (!getInput.equals("falseInput")) {
					mobileList.add(getInput);
				}
			} while (proceed);
		} while (proceed);
		return mobileList;
	}

	public String scanEmail() {
		getInput = in.next();
		if (getInput.equals("-1")) {
			return "terminate";
		}
		if (Validator.validate(getInput, "^[a-zA-Z0-9.-_]+@[a-zA-Z]+[.][a-zA-Z]{2,10}([.][a-zA-Z]{2,10})*")) {
			return getInput;
		}
		else {
			System.err.println(SqlConstants.INVALID_INPUT);
		}
		return "falseInput";
	}

	public ArrayList<String> scanEmailList() {
		ArrayList<String> emailList = new ArrayList<String>();
		proceed = true;
		do {
			System.out.println("Enter the E-mail. Press -1 if over.");
			do {
				String getInput = scanEmail();
				if (getInput.equals("terminate")) {
					proceed = false;
					continue;
				} else if (!getInput.equals("falseInput")) {
					emailList.add(getInput);
				}
			} while (proceed);
		} while (proceed);
		return emailList;
	}

	public int scanId() {
		int id = in.nextInt();
		in.nextLine();
		return id;
	}

	public void addContacts() {
		this.setFirstName(this.scanName());
		this.setLastName(this.scanName());
		this.setOffice(this.scanOfficeList());
		this.setHome(this.scanHomeList());
		this.setMobile(this.scanMobileList());
		this.setEmail(this.scanEmailList());
		if(ContactsDAO.insertContact(this)) {
			System.out.println("Successfully Added!");
		}
	}

	public void updateContacts() {
		boolean doNotExit = false;
		String updateData="";
		int id=0,option=0;
		do {
			String name = this.scanName();
			if (printDetails(ContactsDAO.getDetails(name,SqlConstants.GET_ALL_DETAILS))) {
				break;
			}
		} while (true);
		do {
			System.out.println(SqlConstants.UPDATE_MENU);
			option = in.nextInt();
			switch (option) {
			case 1:
				System.out.println(SqlConstants.ID_FIRSTNAME+"Updated");
				id = this.scanId();
				updateData = this.scanName();
				if(ContactsDAO.updateFirstName(updateData, id)) {
					System.out.println(SqlConstants.UPDATION_SUCCESS);
				}
				else {
					System.err.println(SqlConstants.UPDATION_ERROR);
				}
				break;
			case 2:
				System.out.println(SqlConstants.ID_LASTNAME+"Updated");
				id = this.scanId();
				updateData = this.scanName();
				if(ContactsDAO.updateLastName(updateData, id)){
					System.out.println(SqlConstants.DELETION_SUCCESS);
				}
				else {
					System.err.println(SqlConstants.DELETION_ERROR);
				}
				break;
			case 3:
				System.out.println(SqlConstants.ID_FIELD+"Updated");
				id = this.scanId();
				if (ContactsDAO.findTypeById(id).equals("office")) {
					System.out.println("Enter Office Number");
					do {
						updateData = this.scanOfficeNumber();
						if (!updateData.equals("falseInput") || !updateData.equals("terminate")) {
							break;
						}
					} while (true);
					
				} else if (ContactsDAO.findTypeById(id).equals("home")) {
					System.out.println("Enter Home Number");
					do {
						updateData = this.scanHomeNumber();
						if (!updateData.equals("falseInput") || !updateData.equals("terminate")) {
							break;
						}
					} while (true);
				} else if (ContactsDAO.findTypeById(id).equals("mobile")) {
					System.out.println("Enter Mobile Number");
					do {
						updateData = this.scanMobileNumber();
						if (!updateData.equals("falseInput") || !updateData.equals("terminate")) {
							break;
						}
					} while (true);
				} else if (ContactsDAO.findTypeById(id).equals("email")) {
					System.out.println("Enter E-mail");
					do {
						updateData = this.scanEmail();
						if (updateData.equals("falseInput") || updateData.equals("terminate")) {
							continue;
						}
						break;
					} while (true);
				}
				if(ContactsDAO.updateNumberList(updateData, id)){
					System.out.println(SqlConstants.UPDATION_SUCCESS);
				}
				else {
					System.err.println(SqlConstants.UPDATION_ERROR);
				}
				break;
			case 4:
				break;
			default:
				System.out.println(SqlConstants.VALID_OPTION);
				doNotExit = true;
				break;
			}
		} while (doNotExit);
	}

	public void deleteContacts() {
		boolean doNotExit = false;
		int option=0, id=0;
		do {
			String name = this.scanName();
			if (printDetails(ContactsDAO.getDetails(name,SqlConstants.GET_ALL_DETAILS))) {
				break;
			}
		} while (true);
		do {
			System.out.println(SqlConstants.DELETE_MENU);
			option = in.nextInt();
			switch (option) {
			case 1:
				System.out.println(SqlConstants.ID_FIRSTNAME+"Deleted");
				id = this.scanId();
				ContactsDAO.deleteName(id);
				break;
			case 2:
				System.out.println(SqlConstants.ID_LASTNAME+"Deleted");
				id = this.scanId();
				ContactsDAO.deleteName(id);
				break;
			case 3:
				System.out.println(SqlConstants.ID_FIELD+"Deleted");
				id = this.scanId();
				ContactsDAO.deleteNumberList(id);
				break;
			case 4:
				break;
			default:
				System.out.println(SqlConstants.VALID_OPTION);
				doNotExit = true;
			}
		} while (doNotExit);
	}

	public void displayContacts() {
		boolean doNotExit = false;
		int option =0;
		do {
			System.out.println(SqlConstants.DISPLAY_MENU);
			option = in.nextInt();
			switch (option) {
			case 1:
				printDetails(ContactsDAO.getDetails(null,SqlConstants.SORT_BY_FNAME));
				break;
			case 2:
				printDetails(ContactsDAO.getDetails(null,SqlConstants.SORT_BY_LNAME));
				break;
			case 3:
				break;
			default:
				System.out.println(SqlConstants.VALID_OPTION);
				doNotExit = true;
			}
		} while (doNotExit);
	}

	public static void displayMenu() {
		int option = 0;
		do {
			System.out.println(SqlConstants.MAIN_MENU);
			option = in.nextInt();
			in.nextLine();
			switch (option) {
			case 1:
				ContactsManager newUser = new ContactsManager();
				newUser.addContacts();
				break;
			case 2:
				ContactsManager updateUser = new ContactsManager();
				updateUser.updateContacts();
				break;
			case 3:
				ContactsManager deleteUser = new ContactsManager();
				deleteUser.deleteContacts();
				break;
			case 4:
				ContactsManager displayUser = new ContactsManager();
				displayUser.displayContacts();
				break;
			case 5:
				break;
			default:
				System.out.println(SqlConstants.VALID_OPTION);
				break;
			}
		} while (option != 5);

	}
	
	public static boolean printDetails(ArrayList<HashMap<String, String>> result) {
		int unique=0;
		if(result.isEmpty()) {
			System.err.println("No Records Found!");
			return false;
		}
		for(HashMap<String, String> map:result) {
			if(unique!=Integer.parseInt(map.get("list_id"))) {
				unique=Integer.parseInt(map.get("list_id"));
				System.out.println("*****************************");
				System.out.println("id: "+map.get("list_id"));
				System.out.println("firstname: "+map.get("firstname"));
				System.out.println("lastname: "+map.get("lastname"));
			}
			System.out.println("id: "+map.get("num_id")+" - "+map.get("type")+": "+map.get("contactdata"));
		}
		System.out.println("*****************************");
		return true;
	}

	public static void main(String args[]) {
		displayMenu();
	}
}
