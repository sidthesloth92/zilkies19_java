package com.zilker.contactsmanager;

import java.util.ArrayList;
import java.util.logging.*;

//package com.zilker.contactsmanager;
import com.zilker.beans.Beans;

public class ContactsManager extends Beans {
	public static final Logger logger = Logger.getLogger("ContactsManager.class");
	public static boolean proceed;
	public static String getInput;

	public String scanFirstname() {
		proceed = true;
		do {
			System.out.println("Enter the First Name");
			getInput = in.nextLine();
			if (validateFirstName(getInput)) {
				proceed = false;
			}
		} while (proceed);
		return getInput;
	}

	public String scanLastname() {
		proceed = true;
		do {
			System.out.println("Enter the Last Name");
			getInput = in.nextLine();
			if (validateLastName(getInput)) {
				proceed = false;
			}
		} while (proceed);
		return getInput;
	}

	public String scanOfficeNumber() {
		getInput = in.next();
		if (getInput.equals("-1")) {
			return "terminate";
		}
		if (validateOfficeNumber(getInput)) {
			return getInput;
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
		if (validateHomeNumber(getInput)) {
			return getInput;
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
		if (validateMobileNumber(getInput)) {
			return getInput;
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
				} else if (!getInput.equals("flaseInput")) {
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
		if (validateEmail(getInput)) {
			return getInput;
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
		int id = in.nextInt();in.nextLine();
		return id;
	}

	public void addContacts() {
		String fname = this.scanFirstname();
		this.setFname(fname);

		String lname = this.scanLastname();
		this.setLname(lname);

		ArrayList<String> office = new ArrayList<String>();
		office = this.scanOfficeList();
		this.setOffice(office);

		ArrayList<String> home = new ArrayList<String>();
		home = this.scanHomeList();
		this.setHome(home);

		ArrayList<String> mobile = new ArrayList<String>();
		mobile = this.scanMobileList();
		this.setMobile(mobile);

		ArrayList<String> email = new ArrayList<String>();
		email = this.scanEmailList();
		this.setEmail(email);
	}

	public void updateContacts() {
		boolean doNotExit = false;
		do {
			String name = this.scanFirstname();
			if (ContactsDAO.printDetails(name)) {
				break;
			}
		} while (true);
		do {
			System.out.println("Choose an Option to Update 1.FirstName 2.LastName 3.Others 4.Exit");
			int option = in.nextInt();
			switch (option) {
			case 1:
				System.out.println("Enter id of the Firstname to be Updated");
				int id = this.scanId();
				String updateData = this.scanFirstname();
				ContactsDAO.updateFirstName(updateData, id);
				break;
			case 2:
				System.out.println("Enter id of the Lastname to be Updated");
				id = this.scanId();
				updateData = this.scanLastname();
				ContactsDAO.updateLastName(updateData, id);
				break;
			case 3:
				System.out.println("Enter id of the field to be Updated");
				id = this.scanId();
				if (ContactsDAO.findTypeById(id).equals("office")) {
					System.out.println("Enter Office Number");
					do {
						updateData = this.scanOfficeNumber();
						if(!updateData.equals("falseInput") || !updateData.equals("terminate")) {
							break;
						}
					}while(true);
					ContactsDAO.updateNumberList(updateData, id);
				} else if (ContactsDAO.findTypeById(id).equals("home")) {
					System.out.println("Enter Home Number");
					do {
						updateData = this.scanHomeNumber();
						if(!updateData.equals("falseInput") || !updateData.equals("terminate")) {
							break;
						}
					}while(true);
					ContactsDAO.updateNumberList(updateData, id);
				} else if (ContactsDAO.findTypeById(id).equals("mobile")) {
					System.out.println("Enter Mobile Number");
					do {
						updateData = this.scanMobileNumber();
						if(!updateData.equals("falseInput") || !updateData.equals("terminate")) {
							break;
						}
					}while(true);
					ContactsDAO.updateNumberList(updateData, id);
				} else if (ContactsDAO.findTypeById(id).equals("email")) {
					System.out.println("Enter E-mail");
					do {
						updateData = this.scanEmail();
						if(updateData.equals("falseInput") || updateData.equals("terminate")) {
							continue;
						}
						break;
					}while(true);
					ContactsDAO.updateNumberList(updateData, id);
				}
				break;
			case 4:
				break;
			default:
				System.out.println("Enter Valid Option");
				doNotExit = true;
				break;
			}
		} while (doNotExit);
	}

	public void deleteContacts() {
		boolean doNotExit = false;
		do {
			String name = this.scanFirstname();
			if (ContactsDAO.printDetails(name)) {
				break;
			}
		} while (true);
		do {
			System.out.println("Choose an Option to Update 1.FirstName 2.LastName 3.Others 4.Exit");
			int option = in.nextInt();
			switch (option) {
			case 1:
				System.out.println("Enter id of the Firstname to be Deleted");
				int id = this.scanId();
				ContactsDAO.deleteName(id);
				break;
			case 2:
				System.out.println("Enter id of the Lastname to be Deleted");
				id = this.scanId();
				ContactsDAO.deleteName(id);
				break;
			case 3:
				System.out.println("Enter id of the field to be Deleted");
				id = this.scanId();
				ContactsDAO.deleteNumberList(id);
				break;
			case 4:
				break;
			default:
				System.out.println("Enter Valid Option");
				doNotExit = true;
			}
		} while (doNotExit);
	}

	public void displayContacts() {
		boolean doNotExit = false;
		do {
			System.out.println("1.Sort by FisrstName 2.Sort By LastName 3.Exit");
			int option = in.nextInt();
			switch (option) {
			case 1:
				ContactsDAO.sortList(1);
				break;
			case 2:
				ContactsDAO.sortList(2);
				break;
			case 3:
				break;
			default:
				System.out.println("Enter Valid Option");
				doNotExit = true;
			}
		} while (doNotExit);
	}

	public static void main(String args[]) {
		int option = 0;
		do {
			System.out.println("1.Add 2.Update 3.Delete 4.Display 5.Exit");
			option = in.nextInt();
			in.nextLine();
			switch (option) {
			case 1:
				ContactsManager newUser = new ContactsManager();
				newUser.addContacts();
				ContactsDAO.insertContact(newUser);
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
				System.out.println("Enter Valid Option");
				break;
			}
		} while (option != 5);

	}
}
