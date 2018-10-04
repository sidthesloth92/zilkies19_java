package io.ztech.hrmanagement.controllers;

import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.hrmanagement.beans.objects.ErrorObject;
import io.ztech.hrmanagement.beans.objects.ResponseObject;
import io.ztech.hrmanagement.delegates.SearchEmployeeDelegate;
import io.ztech.hrmanagement.utils.PersistenceException;

@RestController
public class GatewayController {

	@RequestMapping(value="/login",method=RequestMethod.POST)
	ResponseObject get(@RequestParam() BigInteger empId,
					   @RequestParam() String password) {
		ResponseObject responseObject = new ResponseObject();
		SearchEmployeeDelegate searchEmployeeDelegate = new SearchEmployeeDelegate();
		
		ArrayList<Object> objects = new ArrayList<>();
		
		try {
			String sha256hex = DigestUtils.sha256Hex(password);
			String storedPassword = searchEmployeeDelegate.findPasswordOfEmployeeID(empId);
			if(storedPassword == null) {
				responseObject.setStatus(401);
				objects.add(new ErrorObject("Invalid Employee ID"));
			} else if(sha256hex.equals(storedPassword)) {
				responseObject.setStatus(200);
			} else {
				responseObject.setStatus(401);
				objects.add(new ErrorObject("Invalid Credentials"));
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			responseObject.setStatus(500);
			objects.add(new ErrorObject("Internal Server Error"));
		}
		return responseObject;
	}
	
}
