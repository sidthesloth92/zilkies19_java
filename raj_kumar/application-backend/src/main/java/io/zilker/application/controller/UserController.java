package io.zilker.application.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.Comments;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.User;
import io.zilker.application.beans.UserName;
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.delegate.UserDelegate;

@Controller
@RequestMapping("/users")
public class UserController {
	UserDelegate userDelegate = new UserDelegate();
	Logger logger = Logger.getLogger(UserController.class.getName());

	@RequestMapping(path = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String isLoggedIn(@RequestBody String request) {
		logger.info("Entering isLoggedIn");

		String resultJson = userDelegate.userLogin(request);

		logger.info("Leaving isLoggedIn");
		return resultJson;
	}

	@PostMapping("/register")
	@ResponseBody
	public void userRegistration(@RequestBody User user) throws Exception {
		logger.info("Entering userRegistration");
		userDelegate.userCreationService(user);
		logger.info("Leaving userRegistration");
	}

	@GetMapping("/{username}")
	@ResponseBody
	public UserName isUserPresent(@PathVariable("username") String username) {
		logger.info("Entering isUserPresent");
		UserName user = new UserName();
		if (userDelegate.isUserPresent(username)) {
			user.setStatus("PRESENT");
		} else {
			user.setStatus("NOT_PRESENT");
		}
		logger.info("Leaving isUSerPresent");
		return user;
	}

	@GetMapping("/projects")
	@ResponseBody
	public ArrayList<ApprovedProject> getAllProjects() {
		logger.info("Entering getAllProjects");
		logger.info("Leaving getAllProjects");
		return userDelegate.displayProjects();
	}

	@GetMapping("/{userID}/projects")
	@ResponseBody
	public ArrayList<ApprovedProject> projectsInLocation(@PathVariable("userID") Integer userID) {
		logger.info("Entering projectsInLocation");
		logger.info("Leaving projectsInLocation");
		return userDelegate.projectsInLocationSer(userID);
	}

	@GetMapping("/projects/{projectID}")
	@ResponseBody
	public ArrayList<ApprovedProject> projectDetail(@PathVariable("projectID") Integer projectID) {
		logger.info("Entering projectDetail");
		logger.info("Leaving projectDetail");
		return userDelegate.detailDisplay(projectID);
	}

	@GetMapping("/contractor/{contractorID}")
	@ResponseBody
	public Contractor getContractorDetails(@PathVariable("contractorID") Integer contractorID) {
		return userDelegate.getContractorDetails(contractorID);
	}

	@GetMapping("/projects/{projectID}/comments")
	@ResponseBody
	public ArrayList<Comments> getComments(@PathVariable("projectID") Integer projectID) {
		return userDelegate.getComments(projectID);
	}

	@PostMapping("/comment")
	@ResponseBody
	public void addComment(@RequestBody Comments comment) throws Exception {
		System.out.println(DisplayConstants.SPLITTER);
		System.out.println("Entering into addComment Controller");
		System.out.println(comment.getUserID() + "\n " + comment.getProjectId() + "\n" + comment.getCommentMsg());
		userDelegate.addComment(comment.getUserID(), comment.getProjectId(), comment.getCommentMsg());
	}

}
