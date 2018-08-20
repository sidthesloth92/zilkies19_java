package io.zilker.appstore.controller;

import io.zilker.appstore.beans.*;
import io.zilker.appstore.delegates.*;

public class TesterController {

	ControllerValidator validator;
	TesterDelegates testerDelegates;

	public TesterController() {
		validator = new ControllerValidator();
		testerDelegates = new TesterDelegates();
	}
	
	public boolean hasReferenceText(String text) throws Exception {
		return testerDelegates.hasReferenceText(text);
	}
	
	public boolean testerRegister(Tester tester) throws Exception {
		validator.checkUserName(tester.getUserName());
		validator.checkPassword(tester.getPassword());
		return testerDelegates.registerTester(tester);
	}
	
	public Tester testerLogin (Tester tester) throws Exception {
		validator.checkUserName(tester.getUserName());
		validator.checkPassword(tester.getPassword());
		return testerDelegates.testerLogin(tester);
	}
	
	public boolean changeUserName (Tester tester) throws Exception {
		validator.checkID(tester.getTesterID());
		validator.checkUserName(tester.getUserName());
		validator.checkPassword(tester.getPassword());
		return testerDelegates.changeUserName(tester);
	}
	
	public boolean changePassword (Tester tester) throws Exception {
		validator.checkID(tester.getTesterID());
		validator.checkUserName(tester.getUserName());
		validator.checkPassword(tester.getPassword());
		return testerDelegates.changePassword(tester);
	}
	
}
