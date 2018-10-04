package io.ztech.autorate.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.Statistics;
import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.delegate.Validator;
import io.ztech.autorate.services.AddCarService;
import io.ztech.autorate.services.AddRatingService;
import io.ztech.autorate.services.AddStatisticsService;
import io.ztech.autorate.services.CheckLoginService;
import io.ztech.autorate.services.CheckMakeCarTypeIDService;
import io.ztech.autorate.services.DeleteCarService;
import io.ztech.autorate.services.FetchDetailsService;
import io.ztech.autorate.services.FetchStatisticsService;
import io.ztech.autorate.services.LoginService;
import io.ztech.autorate.services.RequestCarService;

public class UIClass {

	FetchDetailsService fetchdetailsService = new FetchDetailsService();
	AddCarService addCarService = new AddCarService();
	CheckLoginService checkLoginService = new CheckLoginService();
	LoginService loginService = new LoginService();
	DeleteCarService deleteCarService = new DeleteCarService();
	AddStatisticsService addStatisticsService = new AddStatisticsService();
	AddRatingService addRatingService = new AddRatingService();
	RequestCarService requestCarService = new RequestCarService();
	FetchStatisticsService fetchStatisticsService = new FetchStatisticsService();
	CheckMakeCarTypeIDService checkMakeCarTypeIDService = new CheckMakeCarTypeIDService();

	public static final Logger logger = Logger.getLogger(UIClass.class.getName());
	public static final Scanner sc = new Scanner(System.in);

	Make make = new Make();
	CarType carType = new CarType();
	User user = new User();
	Specification specification = new Specification();
	Statistics statistics = new Statistics();
	Rating rating = new Rating();
	Request request = new Request();

	public boolean getLoginUI() {
		while (true) {
			logger.info(AppConstants.ENTER_USERNAME);
			user.setUsername(sc.next());
			logger.info(AppConstants.ENTER_PASSWORD);
			user.setPassword(sc.next());
			try {
				if (!checkLoginService.isUser(user)) {
					logger.info(AppConstants.NO_USER_SIGNUP);
					if (sc.next().equals(AppConstants.Y))
						this.getSignupUI();
				} else
					break;
			} catch (SQLException e) {
				logger.info(AppConstants.LOGIN_ERROR);
				continue;
			}
		}

		try {
			if (checkLoginService.isAdmin(user)) {
				return this.putLoginAdminUI();
			} else {
				return this.putLoginUserUI();
			}
		} catch (SQLException e) {
			logger.info(AppConstants.LOGIN_ERROR);
			return false;
		}
	}

	public boolean putLoginUserUI() {

		user.setAdminStatus(AppConstants.USER);
		loginService.login(user);
		this.userMenu();
		return true;
	}

	public boolean putLoginAdminUI() {

		user.setAdminStatus(AppConstants.ADMIN);
		loginService.login(user);
		this.adminMenu();
		return true;
	}

	public boolean getSignupUI() {

		while (true) {
			logger.info(AppConstants.ENTER_FIRSTNAME);
			user.setFirstName(sc.next());
			logger.info(AppConstants.ENTER_LASTNAME);
			user.setLastName(sc.next());
			do {
				logger.info(AppConstants.ENTER_USERNAME);
				user.setUsername(sc.next());
			} while (!Validator.validate("",user.getUsername()));
			logger.info(AppConstants.ENTER_EMAILID);
			user.setEmailId(sc.next());
			do {
				logger.info(AppConstants.ENTER_PASSWORD);
				user.setPassword(sc.next());
			} while (!Validator.validate("",user.getPassword()));
			try {
				if (checkLoginService.isUser(user)) {
					logger.info(AppConstants.USERNAME_EXISTS);
				} else
					break;
			} catch (SQLException e) {
				logger.info(AppConstants.ERROR_DATA);
				return false;
			}
		}
		return this.putSignupUI();
	}

	public boolean putSignupUI() {
		try {
			if (!loginService.signup(user)) {
				return false;
			}
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		this.getLoginUI();
		return true;
	}

	public boolean getMakeUI() {
		if (!this.displayMakesUI())
			return false;
		while (true) {
			logger.info(AppConstants.ENTER_MAKE_ID);
			make.setMakeId(sc.nextInt());
			try {
				if (checkMakeCarTypeIDService.isMakeId(make, carType)) {
					this.putMakeUI(make.getMakeId());
					break;
				}
			} catch (SQLException e) {
				logger.info(AppConstants.ERROR_DATA);
				return false;
			}
			logger.info(AppConstants.INVALID_INPUT);
		}
		return true;

	}

	public boolean putMakeUI(int makeId) {
		make.setMakeId(makeId);
		if (!this.displayCarsUI()) {
			return this.getCarTypeUI();
		}
		return true;
	}

	public boolean getCarTypeUI() {
		if (!this.displayCarTypesUI())
			return false;
		while (true) {
			logger.info(AppConstants.ENTER_CAR_TYPE_ID);
			carType.setCarTypeId(sc.nextInt());
			try {
				if (checkMakeCarTypeIDService.isCarTypeId(make, carType)) {
					this.putCarTypeUI(carType.getCarTypeId());
					break;
				}
			} catch (SQLException e) {
				logger.info(AppConstants.ERROR_DATA);
				return false;
			}
			logger.info(AppConstants.INVALID_INPUT);
		}
		return true;
	}

	public boolean putCarTypeUI(int carTypeId) {
		carType.setCarTypeId(carTypeId);
		if (!this.displayCarsUI())
			return this.getMakeUI();
		return true;
	}

	public boolean displayCarsUI() {
		ArrayList<Specification> cars;
		try {
			cars = fetchdetailsService.getCars(make, carType);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		if (cars == null)
			return false;
		for (Specification specification : cars) {
			if (specification.getCarStatus().equals(AppConstants.UNAPPROVED)
					&& !user.getAdminStatus().equals(AppConstants.ADMIN)) {
				continue;
			}
			if (specification.getCarStatus().equals(AppConstants.APPROVED) && request.getRequestId() == 1) {
				continue;
			}
			this.printCar(specification);
		}
		if (user.getAdminStatus().equals(AppConstants.ADMIN) || request.getRequestId() == 1) {
			return true;
		}
		logger.info(AppConstants.ENTER_CAR_ID);
		specification.setCarId(sc.nextInt());
		return this.carMenu();

	}

	public boolean displayMakesUI() {
		ArrayList<Make> makes;
		try {
			makes = fetchdetailsService.displayMakes(carType);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		if (makes == null)
			return false;
		for (Make make : makes) {
			logger.info(make.getMakeId() + " " + make.getMakeName());
		}
		return true;
	}

	public boolean displayCarTypesUI() {
		ArrayList<CarType> carTypes;
		try {
			carTypes = fetchdetailsService.displayCarTypes(make);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		if (carTypes == null)
			return false;
		for (CarType carType : carTypes) {
			logger.info(carType.getCarTypeId() + " " + carType.getCarTypeName());
		}
		return true;
	}

	public boolean displayRatingUI() {
		HashMap<User, Rating> ratings = null;
		try {
			ratings = fetchdetailsService.displayRating(specification);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		if (ratings == null) {
			logger.info(AppConstants.NO_RATINGS);
			return false;
		}
		int averageRating = 0;
		for (Map.Entry<User, Rating> entry : ratings.entrySet()) {
			logger.info("Username:" + entry.getKey().getUsername() + "\nRating:" + entry.getValue().getRating()
					+ "\nReview:" + entry.getValue().getReview());
			averageRating += Integer.parseInt(entry.getValue().getRating());
		}
		logger.info(AppConstants.AVERAGE_RATINGS + (float) (averageRating / ratings.size()));
		return true;
	}

	public boolean displayStatisticsUI() {
		ArrayList<Statistics> statistics = null;
		try {
			statistics = fetchStatisticsService.displayAllStatisticsCar(specification);
		} catch (Exception e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		for (Statistics statistic : statistics)
			logger.info(AppConstants.PRINT_YEAR + statistic.getStatisticsYear() + "\n" + statistic.getSaleCount());
		return true;
	}

	public boolean addStatisticsUI() {
		this.getMakeUI();
		logger.info(AppConstants.ENTER_CAR_ID_UPDATE);
		specification.setCarId(sc.nextInt());
		logger.info(AppConstants.ENTER_STATISTICS_YEAR);
		statistics.setStatisticsYear(sc.nextInt());
		logger.info(AppConstants.ENTER_SALES_COUNT);
		statistics.setSaleCount(sc.nextInt());
		try {
			addStatisticsService.addStatistics(specification, statistics);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		return true;
	}

	public boolean getRatingUI() {
		logger.info(AppConstants.ENTER_RATING);
		rating.setRating(sc.next());
		sc.nextLine();
		logger.info(AppConstants.ENTER_REVIEW);
		rating.setReview(sc.nextLine());
		return true;
	}

	public boolean addRatingUI() {
		this.getRatingUI();
		try {
			if (!addRatingService.addRating(specification, rating, user)) {
				logger.info(AppConstants.EDIT_RATING);
				if (sc.next().equalsIgnoreCase(AppConstants.Y))
					return this.editRatingUI();
			}
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		return true;
	}

	public boolean editRatingUI() {
		this.getRatingUI();
		return addRatingService.editRating(specification, rating, user);
	}

	public boolean addCarUI() {

		this.displayCarTypesUI();
		logger.info(AppConstants.ENTER_CAR_TYPE_ID_NEW);
		carType.setCarTypeId(sc.nextInt());
		if (carType.getCarTypeId() == 0 && request.getRequestId() == 0) {
			logger.info(AppConstants.ENTER_CAR_TYPE_NAME);
			carType.setCarTypeName(sc.next());
		}
		this.displayMakesUI();
		logger.info(AppConstants.ENTER_MAKE_ID_NEW);
		make.setMakeId(sc.nextInt());
		if (make.getMakeId() == 0) {
			logger.info(AppConstants.ENTER_MAKE_NAME);
			make.setMakeName(sc.next());
		}
		logger.info(AppConstants.ENTER_CAR_NAME);
		specification.setCarName(sc.next());
		logger.info(AppConstants.ENTER_ENGINE_TYPE);
		specification.setEngineType(sc.next());
		logger.info(AppConstants.ENTER_CYLINDERS);
		specification.setCylinder(sc.nextInt());
		logger.info(AppConstants.ENTER_DISPLACEMENT);
		specification.setDisplacement(sc.nextInt());
		logger.info(AppConstants.ENTER_TRANSMISSION);
		specification.setTransmission(sc.nextInt());
		logger.info(AppConstants.ENTER_POWER);
		specification.setPower(sc.nextInt());
		logger.info(AppConstants.ENTER_TORQUE);
		specification.setTorque(sc.nextInt());
		logger.info(AppConstants.ENTER_FUEL_CAPACITY);
		specification.setFuelCapacity(sc.nextInt());
		logger.info(AppConstants.WHEELBASE);
		specification.setWheelbase(sc.nextInt());
		logger.info(AppConstants.ENTER_KERB_WEIGHT);
		specification.setKerbWeight(sc.nextInt());
		logger.info(AppConstants.ENTER_AIRBAG);
		specification.setAirbag(sc.next());
		logger.info(AppConstants.ENTER_ABS);
		specification.setAbs(sc.next());
		logger.info(AppConstants.ENTER_DRIVETRAIN);
		specification.setDrivetrain(sc.next());
		logger.info(AppConstants.ENTER_PRICE);
		specification.setPrice(sc.nextInt());
		if (user.getAdminStatus().equals(AppConstants.ADMIN)) {
			specification.setCarStatus(AppConstants.APPROVED);
		} else {
			specification.setCarStatus(AppConstants.UNAPPROVED);
		}
		try {
			addCarService.addCar(carType, make, specification);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		return true;
	}

	public boolean deleteCarUI() {
		if (request.getRequestId() == 0) {
			if (!this.getMakeUI())
				return false;
			logger.info(AppConstants.ENTER_CAR_ID_DELETE);
			specification.setCarId(sc.nextInt());
		}
		logger.info(AppConstants.CONFIRM_DELETE);
		if (sc.next().equals(AppConstants.Y))
			try {
				return deleteCarService.deleteCar(specification);
			} catch (SQLException e) {
				logger.info(AppConstants.ERROR_DATA);
				return false;
			}
		else
			return true;
	}

	public boolean addCarUserRequestUI() {
		request.setRequestId(1);
		if (!this.addCarUI()) {
			return false;
		}
		try {
			requestCarService.addCarUserRequest(request, user, specification);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			request.setRequestId(0);
			return false;
		}
		logger.info(AppConstants.YOUR_REQUEST_ID + request.getRequestId());
		return true;

	}

	public boolean getRequests() {
		ArrayList<Request> requests = null;
		try {
			requests = requestCarService.getRequests(user);
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		if (requests == null) {
			logger.info(AppConstants.NO_PENDING_REQUESTS);
			return false;
		}
		for (Request request : requests) {
			logger.info(request.getRequestId() + " " + request.getCarId() + " " + request.getUserName());
		}
		return true;
	}

	public boolean printCar(Specification specification) {

		logger.info(AppConstants.PRINT_CAR_ID + specification.getCarId() + AppConstants.PRINT_CAR_NAME
				+ specification.getCarName() + AppConstants.PRINT_ENGINE_TYPE + specification.getEngineType()
				+ AppConstants.PRINT_ENGINE_TYPE + specification.getCylinder() + AppConstants.PRINT_DISPLACEMENT
				+ specification.getDisplacement() + AppConstants.PRINT_TRANSMISSION + specification.getTransmission()
				+ AppConstants.PRINT_POWER + specification.getPower() + AppConstants.PRINT_TORQUE
				+ specification.getTorque() + AppConstants.PRINT_FUEL_CAPACITY + specification.getFuelCapacity()
				+ AppConstants.PRINT_WHEELBASE + specification.getWheelbase() + AppConstants.PRINT_KERB_WEIGHT
				+ specification.getKerbWeight() + AppConstants.PRINT_AIRBAG + specification.getAirbag()
				+ AppConstants.PRINT_ABS + specification.getAbs() + AppConstants.PRINT_DRIVETRAIN
				+ specification.getDrivetrain() + AppConstants.PRINT_PRICE + specification.getPrice());
		return true;
	}

	public boolean reviewAddRequestsUI() {
		if (!this.getRequests())
			return false;
		logger.info(AppConstants.ENTER_REQUEST_ID_REVIEW);
		request.setRequestId(sc.nextInt());

		try {
			this.printCar(fetchdetailsService.getCar(request));
		} catch (SQLException e) {
			logger.info(AppConstants.ERROR_DATA);
			return false;
		}
		logger.info(AppConstants.APPROVE_DELETE);
		if (sc.next().equalsIgnoreCase(AppConstants.A)) {
			try {
				requestCarService.approveCar(specification, request);
			} catch (SQLException e) {
				logger.info(AppConstants.ERROR_DATA);
				return false;
			}
		} else if (sc.next().equalsIgnoreCase(AppConstants.D)) {
			this.deleteCarUI();
		}
		return true;
	}

	public boolean carMenu() {
		while (true) {
			int choice = 0;
			try {
				logger.info(AppConstants.CAR_MENU);
				choice = sc.nextInt();
			} catch (Exception e) {
				logger.info(AppConstants.INVALID_INPUT);
				sc.nextLine();
			}
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
					logger.info(AppConstants.LOGIN_RATING);
					return true;
				}
				this.addRatingUI();
				break;
			}
			case 4: {
				if (!User.getLoginStatus()) {
					logger.info(AppConstants.LOGIN_RATING);
					return true;
				}
				if (this.editRatingUI()) {
					logger.info(AppConstants.EDIT_SUCCESSFUL);
				}
				break;
			}
			case 5: {
				return true;
			}
			}
		}
	}

	public boolean userMenu() {
		while (true) {
			int choice = 0;
			try {
				logger.info(AppConstants.USER_MENU);
				choice = sc.nextInt();
			} catch (Exception e) {
				logger.info(AppConstants.INVALID_INPUT);
				sc.nextLine();
			}
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
				loginService.logout(user);
				return true;
			}
			}
		}
	}

	public boolean adminMenu() {
		while (true) {
			int choice = 0;
			try {
				logger.info(AppConstants.ADMIN_MENU);
				choice = sc.nextInt();
			} catch (Exception e) {
				logger.info(AppConstants.INVALID_INPUT);
				sc.nextLine();
			}
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
					logger.info(AppConstants.NO_PENDING_REQUESTS);
				break;
			}
			case 5: {
				this.clearAll(make, carType);
				loginService.logout(user);
				return true;
			}
			}
		}
	}

	public boolean mainMenu() {
		while (true) {
			int choice = 0;
			try {
				logger.info(AppConstants.MAIN_MENU);
				choice = sc.nextInt();
			} catch (Exception e) {
				logger.info(AppConstants.INVALID_INPUT);
				sc.nextLine();
			}
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
					logger.info(AppConstants.LOGIN_ERROR);
				}
				break;
			}
			case 4: {
				while (!this.getSignupUI()) {
					logger.info(AppConstants.SIGNUP_ERROR);
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
