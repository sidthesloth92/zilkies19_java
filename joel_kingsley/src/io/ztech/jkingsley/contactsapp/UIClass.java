package io.ztech.jkingsley.contactsapp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.jkingsley.contactsapp.beans.Contact;
import io.ztech.jkingsley.contactsapp.beans.Email;
import io.ztech.jkingsley.contactsapp.beans.PhoneNumber;
import io.ztech.jkingsley.contactsapp.dao.DBLibrary;

public class UIClass implements KeyListener{

	private static final Logger LOGGER = Logger.getLogger(UIClass.class.getName());

	public static void main(String[] args) {
		int choice = 0;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n------");
			System.out.println("Menu");
			System.out.println("1. Add Contact");
			System.out.println("2. Update Contact");
			System.out.println("3. Display User");
			System.out.println("Enter any other key to exit.");

			while(!scanner.hasNext());
			
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				Contact contact = InputHandler.inputForAddContact();
				DBLibrary.addContact(contact);
				break;
				
			case 2:
				Long userId = InputHandler.inputIdForUpdateContact();
				System.out.println("Selected user ID is " + userId);
				
				int updateType = InputHandler.inputTypeOfUpdate();
				
				switch(updateType) {
				case 1:
					String firstName = InputHandler.inputNewFirstName();
					DBLibrary.updateFirstName(userId, firstName);
					System.out.println("First Name Updated");
					break;
				case 2:
					String lastName = InputHandler.inputNewLastName();
					DBLibrary.updateLastName(userId, lastName);
					System.out.println("Last Name Updated");
					break;
				case 3:
					ArrayList<PhoneNumber> phoneNumbers = DBLibrary.getPhoneNumbersOfUser(userId);
					for(int i=0;i<phoneNumbers.size();i++) {
						System.out.println(i+1 + ". " + phoneNumbers.get(i).toString());
					}
					int numberSelected = InputHandler.selectNumberToUpdate(phoneNumbers.size());
					Long phoneNumber = InputHandler.inputNewPhoneNumber(phoneNumbers.get(numberSelected));
					DBLibrary.updatePhoneNumber(userId,phoneNumbers.get(numberSelected),phoneNumber);
					System.out.println("Phone number updated");
					break;
				case 4:
					ArrayList<Email> emails = DBLibrary.getEmailsOfUser(userId);
					for(int i=0;i<emails.size();i++) {
						System.out.println(i+1 + ". " + emails.get(i).getAddress());
					}
					int numberSelected1 = InputHandler.selectNumberToUpdate(emails.size());
					String email = InputHandler.inputNewEmail(emails.get(numberSelected1));
					DBLibrary.updateEmail(userId,emails.get(numberSelected1),email);
					System.out.println("Email Id Updated");
					break;
				}
			
				break;
			
			case 3:
				Long userId1 = InputHandler.inputIdForDisplayContact();
				System.out.println("Selected user ID is " + userId1);
				Contact contact1 = DBLibrary.getContact(userId1);
				System.out.println("Name: " + contact1.user.getFirstName() + " " + contact1.user.getLastName());
				System.out.println("Home Numbers:");
				for(int i=0;i<contact1.homeNumbers.size();i++) {
					System.out.print(contact1.homeNumbers.get(i).getNumber().toString() + ", ");
				}
				System.out.println("");
				System.out.println("Mobile Numbers:");
				for(int i=0;i<contact1.mobileNumbers.size();i++) {
					System.out.print(contact1.mobileNumbers.get(i).getNumber().toString() + ", ");
				}
				System.out.println("");
				System.out.println("Office Numbers:");
				for(int i=0;i<contact1.officeNumbers.size();i++) {
					System.out.print(contact1.officeNumbers.get(i).getNumber().toString() + ", ");
				}
				System.out.println("");
				System.out.print("Email:");
				for(int i=0;i<contact1.emails.size();i++) {
					System.out.print(contact1.emails.get(i).getAddress() + ", ");
				}
				
				break;
			default:
				scanner.close();
				System.out.println("Exiting...");
				return;
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 27) {
			System.out.println("ESC pressed");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
