package io.ztech.carstats.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.carstats.beans.*;
import io.ztech.carstats.constants.AppConstants;
import io.ztech.carstats.delegate.*;
import io.ztech.carstats.services.*;

public class UIClass {

	FetchDetailsService fetchdetailsService = new FetchDetailsService();
	AddCarService addCarService = new AddCarService();
	CheckLoginService checkLoginService = new CheckLoginService();
	LoginService loginService = new LoginService();
	DeleteCarService deleteCarService = new DeleteCarService();
	AddStatisticsService addStatisticsService = new AddStatisticsService();
	AddRatingService addRatingService = new AddRatingService();
	RequestCarService requestCarService = new RequestCarService();

	public static final Logger logger = Logger.getLogger(UIClass.class.getName());
	public static final Scanner sc = new Scanner(System.in);

	Make make = new Make();
	CarType carType = new CarType();
	User user = new User();
	Specification specification = new Specification();
	Statistics statistics = new Statistics();
	Rating rating = new Rating();
	Request request = new Request();

	public boolean getLoginUI() throws SQLException {
		while (true) {
			logger.info("Enter user name:");
			user.setUserName(sc.next());
			logger.info("Enter password:");
			user.setPassword(sc.next());
			if (!checkLoginService.isUser(user)) {
				logger.info("User doesn't exist wanna signin?(y/n)");
				if (sc.next().equals("y"))
					this.getSignupUI();
			} else
				break;
		}

		if (checkLoginService.isAdmin(user)) {
			return this.putLoginAdminUI();
		} else {
			return this.putLoginUserUI();
		}
	}

	public boolean putLoginUserUI() throws SQLException {

		user.setAdminStatus(AppConstants.USER);
		loginService.login();
		this.userMenu();
		return true;
	}

	public boolean putLoginAdminUI() throws SQLException {

		user.setAdminStatus(AppConstants.ADMIN);
		loginService.login();
		this.adminMenu();
		return true;
	}

	public boolean getSignupUI() throws SQLException {

		do {
			do {
				logger.info("Enter user name:");
				user.setUserName(sc.next());
			} while (!Validator.validateUsername(user.getUserName()));
			do {
				logger.info("Enter password:");
				user.setPassword(sc.next());
			} while (!Validator.validatePassword(user.getPassword()));
			if (checkLoginService.isUser(user))
				logger.info("Username already exists, please try a new one!");
		} while (checkLoginService.isUser(user));
		return this.putSignupUI();
	}

	public boolean putSignupUI() throws SQLException {
		if (!loginService.signup(user)) {
			return false;
		}
		this.getLoginUI();
		return true;
	}

	public boolean getMakeUI() throws SQLException {
		if (!this.displayMakesUI())
			return false;
		logger.info("Enter Make ID:");
		this.putMakeUI(sc.nextInt());
		return true;
	}

	public boolean putMakeUI(int makeId) throws SQLException {
		make.setMakeId(makeId);
		if (!this.displayCarsUI()) {
			return this.getCarTypeUI();
		}
		return true;
	}

	public boolean getCarTypeUI() throws SQLException {
		if (!this.displayCarTypesUI())
			return false;
		logger.info("Enter Car Type ID:");
		this.putCarTypeUI(sc.nextInt());
		return true;
	}

	public boolean putCarTypeUI(int carTypeId) throws SQLException {
		carType.setCarTypeId(carTypeId);
		if (!this.displayCarsUI())
			return this.getMakeUI();
		return true;
	}

	public boolean displayCarsUI() throws SQLException {
		HashMap<Integer, ArrayList<String>> cars = fetchdetailsService.getCars(make, carType);
		if (cars == null)
			return false;
		for (Map.Entry<Integer, ArrayList<String>> entry : cars.entrySet()) {
			ArrayList<String> specs = entry.getValue();
			if (entry.getValue().get(14).equals("UNAPPROVED") && !user.getAdminStatus().equals("ADMIN")) {
				continue;
			}
			if (entry.getValue().get(14).equals("APPROVED") && request.getRequestId() == 1) {
				continue;
			}
			logger.info(entry.getKey() + " ");
			for (String spec : specs) {
				logger.info(spec + " ");
			}

		}
		if (user.getAdminStatus().equals("ADMIN") || request.getRequestId() == 1) {
			return true;
		}
		logger.info("Enter Car ID:");
		specification.setCarId(sc.nextInt());
		return this.carMenu();

	}

	public boolean displayMakesUI() throws SQLException {
		HashMap<Integer, String> makes = fetchdetailsService.displayMakes();
		if (makes == null)
			return false;
		for (Map.Entry<Integer, String> entry : makes.entrySet()) {
			logger.info(entry.getKey() + " " + entry.getValue());
		}
		return true;
	}

	public boolean displayCarTypesUI() throws SQLException {
		HashMap<Integer, String> carTypes = fetchdetailsService.displayCarTypes();
		if (carTypes == null)
			return false;
		for (Map.Entry<Integer, String> entry : carTypes.entrySet()) {
			logger.info(entry.getKey() + " " + entry.getValue());
		}
		return true;
	}

	public boolean displayRatingUI() {
		HashMap<String, Rating> ratings = fetchdetailsService.displayRating(specification);
		for (Map.Entry<String, Rating> entry : ratings.entrySet()) {
			logger.info("Username:" + entry.getKey() + "\nRating:" + entry.getValue().getRating() + "\nReview:"
					+ entry.getValue().getReview());
		}
		return true;
	}

	public boolean displayStatisticsUI() {
		ArrayList<Statistics> statistics = fetchdetailsService.displayStatistics(specification);
		for (Statistics statistic : statistics)
			logger.info("Year:" + statistic.getStatisticsYear() + "\n" + statistic.getSaleCount());
		return true;
	}

	public void addStatisticsUI() throws SQLException {
		this.getMakeUI();
		logger.info("Enter Car ID to update:");
		specification.setCarId(sc.nextInt());
		logger.info("Enter Statistics Year:");
		statistics.setStatisticsYear(sc.nextInt());
		logger.info("Enter Sales Count:");
		statistics.setSaleCount(sc.nextInt());
		addStatisticsService.addStatistics(specification, statistics);
	}

	public void addRatingUI() throws SQLException {
		logger.info("Enter Rating(1-5):");
		rating.setRating(sc.next());
		sc.nextLine();
		logger.info("Enter Review(500 words):");
		rating.setReview(sc.nextLine());
		addRatingService.addRating(specification, rating, user);
	}

	public void addCarUI() throws SQLException {

		this.displayCarTypesUI();
		logger.info("Car Type ID(if new enter 0):");
		carType.setCarTypeId(sc.nextInt());
		if (carType.getCarTypeId() == 0) {
			logger.info("Enter new car type:");
			carType.setCarTypeName(sc.next());
		}
		this.displayMakesUI();
		logger.info("Make ID(if new enter 0):");
		make.setMakeId(sc.nextInt());
		if (make.getMakeId() == 0) {
			logger.info("Enter new make name:");
			make.setMakeName(sc.next());
		}
		logger.info("Car Name:");
		specification.setCarName(sc.next());
		logger.info("Engine type(PETROL,DIESEL):");
		specification.setEngineType(sc.next());
		logger.info("Cylinders:");
		specification.setCylinder(sc.nextInt());
		logger.info("Displacement:");
		specification.setDisplacement(sc.nextInt());
		logger.info("Transmission:");
		specification.setTransmission(sc.nextInt());
		logger.info("Power:");
		specification.setPower(sc.nextInt());
		logger.info("Torque:");
		specification.setTorque(sc.nextInt());
		logger.info("Fuel Capacity:");
		specification.setFuelCapacity(sc.nextInt());
		logger.info("Wheelbase:");
		specification.setWheelbase(sc.nextInt());
		logger.info("Kerb Weight:");
		specification.setKerbWeight(sc.nextInt());
		logger.info("Airbag:");
		specification.setAirbag(sc.next());
		logger.info("ABS:");
		specification.setAbs(sc.next());
		logger.info("Drivetrain(FWD,RWD,AWD):");
		specification.setDrivetrain(sc.next());
		logger.info("Price:");
		specification.setPrice(sc.nextInt());
		if (user.getAdminStatus().equals("ADMIN")) {
			specification.setCarStatus("APPROVED");
		} else {
			specification.setCarStatus("UNAPPROVED");
		}
		addCarService.addCar(carType, make, specification);
	}

	public boolean deleteCarUI() throws SQLException {
		if (request.getRequestId() == 0) {
			if (!this.getMakeUI())
				return false;
			logger.info("Enter Car ID to delete:");
			specification.setCarId(sc.nextInt());
		}
		logger.info("Confirm Delete?(y/n)");
		if (sc.next().equals("y"))
			return deleteCarService.deleteCar(specification);
		else
			return true;
	}

	public boolean addCarUserRequestUI() throws SQLException {
		request.setRequestId(1);
		this.addCarUI();

		if (!requestCarService.addCarUserRequest(request, user, specification))
			return false;
		else {
			logger.info("Your Request ID:" + request.getRequestId());
			return true;
		}
	}

	public boolean getRequests() {
		ArrayList<Request> requests = requestCarService.getRequests(user);
		if (requests == null)
			return false;
		for (Request request : requests) {
			logger.info(request.getRequestId() + " " + request.getCarId() + " " + request.getUserName());
		}
		return true;
	}

	public void getCarUI(Request request) {
		specification = fetchdetailsService.getCar(request);
		logger.info(specification.getCarId() + " " + specification.getCarName() + " " + specification.getEngineType()
				+ " " + specification.getCylinder() + " " + specification.getDisplacement() + " "
				+ specification.getTransmission() + " " + specification.getPower() + " " + specification.getTorque()
				+ " " + specification.getFuelCapacity() + " " + specification.getWheelbase() + " "
				+ specification.getKerbWeight() + " " + specification.getAirbag() + " " + specification.getAbs() + " "
				+ specification.getDrivetrain() + " " + specification.getPrice() + " " + specification.getCarStatus());
	}

	public boolean reviewAddRequestsUI() throws SQLException {
		if (!this.getRequests())
			return false;
		logger.info("Enter Request ID to review:");
		request.setRequestId(sc.nextInt());
		this.getCarUI(request);
		logger.info("Approve or Delete?(a/d)");
		if (sc.next().equalsIgnoreCase("a")) {
			requestCarService.approveCar(specification, request);
		} else {
			this.deleteCarUI();
		}
		return true;
	}

	public boolean carMenu() throws SQLException {
		while (true) {
			logger.info("1.Ratings&Reviews\n2.Statistics\n3.Add Rating\n4.Main Menu");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				this.displayRatingUI();
				break;
			}
			case 2: {
				this.displayStatisticsUI();
				break;
			}
			case 3: {
				if (!User.getLoginStatus()) {
					logger.info("Please login to add your rating!!");
					return true;
				}
				this.addRatingUI();
				break;
			}
			case 4: {
				return true;
			}
			}
		}
	}

	public boolean userMenu() throws SQLException {
		while (true) {
			logger.info("1.Car by Make\n2.Car by Type\n3.Request Add Car\n4.Show Pending Requests\n5.Logout");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				this.clearAll(make, carType);
				this.getMakeUI();
				break;
			}
			case 2: {
				this.clearAll(make, carType);
				this.getCarTypeUI();
				break;
			}
			case 3: {
				this.clearAll(make, carType);
				this.addCarUserRequestUI();
				break;
			}
			case 4: {
				this.getRequests();
				break;
			}
			case 5: {
				this.clearAll(make, carType);
				loginService.logout();
				return true;
			}
			}
		}
	}

	public boolean adminMenu() throws SQLException {
		while (true) {
			logger.info("1.Add Car\n2.Delete Car\n3.Add Statistics\n4.Review Add Car Requests\n5.Logout");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				this.clearAll(make, carType);
				this.addCarUI();
				break;
			}
			case 2: {
				this.clearAll(make, carType);
				this.deleteCarUI();
				break;
			}
			case 3: {
				this.clearAll(make, carType);
				this.addStatisticsUI();
				break;
			}
			case 4: {
				this.clearAll(make, carType);
				if (!this.reviewAddRequestsUI())
					logger.info("No requests to review!");
				break;
			}
			case 5: {
				this.clearAll(make, carType);
				loginService.logout();
				return true;
			}
			}
		}
	}

	public boolean mainMenu() throws SQLException {
		while (true) {

			logger.info("1.Car by Make\n2.Car by Type\n3.Login\n4.Signup\n5.Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				this.clearAll(make, carType);
				this.getMakeUI();
				break;
			}
			case 2: {
				this.clearAll(make, carType);
				this.getCarTypeUI();
				break;
			}
			case 3: {
				while (!this.getLoginUI()) {
					logger.info("Login Error! Try Again");
				}
				break;
			}
			case 4: {
				while (!this.getSignupUI()) {
					logger.info("Signup Error! Try Again");
				}
				break;
			}
			case 5: {
				sc.close();
				return true;
			}
			}
		}
	}

	public boolean clearAll(Make make, CarType carType) {
		make.setMakeId(0);
		make.setMakeName(null);
		carType.setCarTypeId(0);
		carType.setCarTypeName(null);
		return true;
	}
}
