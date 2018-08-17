package io.ztech.onlinebidding.ui;

import io.ztech.onlinebidding.bean.CustomerDetail;
import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.services.NewUserRegisterService;
import io.ztech.onlinebidding.utils.FetchUserDetails;

public class RegisterDetails implements ConstantDisplayStatement {
	CustomerDetail details = new CustomerDetail();
	FetchUserDetails fetchDetailsFromUser = new FetchUserDetails();
	NewUserRegisterService newUserService = new NewUserRegisterService();

	public void fetchRegisterDetails() {
		details.setFirstName(fetchDetailsFromUser.fetchdetails(NAME_REGEX, ENTER_FIRST_NAME, INVALID_FIRST_NAME));
		details.setLastName(fetchDetailsFromUser.fetchdetails(NAME_REGEX, ENTER_LAST_NAME, INVALID_LAST_NAME));
		details.setEmail(fetchDetailsFromUser.fetchdetails(EMAIL_REGEX, ENTER_EMAIL, INVALID_EMAIL));
		details.setMobileNumber(
				fetchDetailsFromUser.fetchdetails(MOBILEPHONE_REGEX, ENTER_MOBILE_NUMBER, INVALID_MOBILE));
		details.setDateOfBirth(fetchDetailsFromUser.fetchdetails(DOB_REGEX, ENTER_DATE_OF_BIRTH, INVALID_DOB));
		details.setTypeOfUser(fetchDetailsFromUser.fetchtype(ENTER_TYPE_OF_USER));
		details.setUserName(fetchDetailsFromUser.fetchdetails(USERNAME_REGEX, ENTER_USERNAME, INVALID_USERNAME));
		details.setPassword(fetchDetailsFromUser.fetchdetails(PASSWORD_REGEX, ENTER_PASSWORD, INVALID_PASSWORD));
		newUserService.newUser(details);
	}
}
