package io.zilker.appstore.delegates;

import io.zilker.appstore.dao.*;
import io.zilker.appstore.exceptions.*;
import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.Errors;

public class TesterDelegates {

	private TesterDAO testerDAO;

	public TesterDelegates() {
		testerDAO = new TesterDAO();
	}

	public boolean hasReferenceText(String text) throws Exception {
		return testerDAO.hasTesterReferenceText(text);
	}

	public boolean registerTester(Tester tester) throws Exception {
		if (!testerDAO.checkTesterUserName(tester)) {
			if (!testerDAO.insertTester(tester))
				throw new BusinessException(Errors.DATABASE_ERR);
		} else
			throw new BusinessException(Errors.USERNAME_EXISTS);
		return true;
	}

	public Tester testerLogin(Tester tester) throws Exception {
		tester = testerDAO.getTester(tester);
		if (tester == null)
			throw new BusinessException(Errors.INVALID_USERNAME_PASSWORD);
		return tester;
	}

	public boolean changeUserName(Tester tester) throws Exception {
		return testerDAO.updateUserName(tester);
	}

	public boolean changePassword(Tester tester) throws Exception {
		return testerDAO.updatePassword(tester);
	}

}
