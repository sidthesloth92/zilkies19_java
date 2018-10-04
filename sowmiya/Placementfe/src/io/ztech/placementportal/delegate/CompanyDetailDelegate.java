package io.ztech.placementportal.delegate;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

import com.example.demo.utility.RequestAndResponseHandler;

import io.ztech.placementportal.bean.Company;

public class CompanyDetailDelegate {
	private Logger log;
	private RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();

	public CompanyDetailDelegate() {
		log = Logger.getLogger("CompanyDetailDelegate.class");
	}

	public boolean addCompanyDetail(JSONObject companyJson) {
		log.info("Entering addCompanyDetail()");
		String url = "http://localhost:8006/demo/company";
		String response = requestAndResponseHandler.postRequestHandler(url, companyJson.toJSONString(), "POST");
		log.info("Exiting addCompanyDetail()");
		return Boolean.parseBoolean(response);
	}

	public boolean updateCompany(JSONObject companyJson) {
		log.info("Entering updateCompany()");
		System.out.println(companyJson);
		String url = "http://localhost:8006/demo/company/"+companyJson.get("companyId");
		String response = requestAndResponseHandler.postRequestHandler(url, companyJson.toJSONString(), "PUT");
		log.info("Exiting updateCompany()");
		return Boolean.parseBoolean(response);
	}

	public boolean deleteCompany(String id) {
		log.info("Entering deleteCompany()");
		String url = "http://localhost:8006/demo/company/" + id;
		String response = requestAndResponseHandler.getRequestHandler(url, "DELETE");
		System.out.println(response);
		log.info("Exiting deleteCompany()");
		return Boolean.parseBoolean(response);

	}

}
