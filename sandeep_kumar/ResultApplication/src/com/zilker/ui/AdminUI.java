package com.zilker.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.Map.Entry;

import com.zilker.delegates.Validator;
import com.zilker.services.AdminServices;
import com.zilker.beans.*;
import com.zilker.constants.DisplayConstants;
import com.zilker.constants.RegexConstants;

public class AdminUI {

	public static Scanner in = new Scanner(System.in);
	private static final Logger LOGGER = Logger.getLogger(AdminUI.class.getName());
	public boolean proceed;
	public int option;
	public String getInput;
	AdminServices adminService = new AdminServices();

	public String scanInput(String displayDisplay, String regex, String errorDisplay) {
		LOGGER.info(displayDisplay);
		proceed = true;
		do {
			getInput = in.nextLine();
			if (getInput.equals("-1")) {
				proceed = false;
				continue;
			}
			if (Validator.validate(getInput, regex)) {
				proceed = false;
			} else {
				System.err.println(errorDisplay);
			}
		} while (proceed);
		return getInput;
	}

	public void addStudent() {
		int departmentId = 0;
		StudentData addStudent = new StudentData();
		addStudent.setStudentRegistrationNumber(Long.parseLong(this.scanInput(DisplayConstants.ENTER_REGNO,
				RegexConstants.INT_REGEX, DisplayConstants.INVALID_INPUT)));
		addStudent.setName(this.scanInput(DisplayConstants.ENTER_NAME, RegexConstants.ALPHA_REGEX,
				DisplayConstants.INVALID_INPUT));
		addStudent.setCollegeCode(this.scanInput(DisplayConstants.ENTER_COLLEGE_CODE, RegexConstants.COLLEGE_CODE_REGEX,
				DisplayConstants.INVALID_INPUT));
		departmentId = Integer
				.parseInt(this.scanInput(DisplayConstants.ENTER_DEPARTMENT, "[1-5]+", DisplayConstants.INVALID_INPUT));
		if (departmentId == 1) {
			addStudent.setDepartment("CSE");
		}
		if (departmentId == 2) {
			addStudent.setDepartment("ECE");
		}
		if (departmentId == 3) {
			addStudent.setDepartment("EEE");
		}
		if (departmentId == 4) {
			addStudent.setDepartment("IT");
		}
		if (departmentId == 5) {
			addStudent.setDepartment("MECH");
		}
		addStudent.setSemester(Integer.parseInt(this.scanInput(DisplayConstants.ENTER_SEMESTER,
				RegexConstants.SEMESTER_REGEX, DisplayConstants.INVALID_INPUT)));
		if (adminService.addStudentDetails(addStudent)) {
			LOGGER.info(DisplayConstants.ADD_SUCCESS);
		} else {
			System.err.println(DisplayConstants.ADD_ERROR);
		}
	}

	public void addFaculty() {
		int departmentId = 0;
		FacultyData addFaculty = new FacultyData();
		addFaculty.setFacultyRegistrationNumber(Long.parseLong(this.scanInput(DisplayConstants.ENTER_REGNO,
				RegexConstants.INT_REGEX, DisplayConstants.INVALID_INPUT)));
		addFaculty.setName(this.scanInput(DisplayConstants.ENTER_NAME, RegexConstants.ALPHA_REGEX,
				DisplayConstants.INVALID_INPUT));
		addFaculty.setCollegeCode(this.scanInput(DisplayConstants.ENTER_COLLEGE_CODE, RegexConstants.COLLEGE_CODE_REGEX,
				DisplayConstants.INVALID_INPUT));
		departmentId = Integer.parseInt(this.scanInput(DisplayConstants.ENTER_DEPARTMENT, RegexConstants.DEPT_REGEX,
				DisplayConstants.INVALID_INPUT));
		if (departmentId == 1) {
			addFaculty.setDepartment("CSE");
		}
		if (departmentId == 2) {
			addFaculty.setDepartment("ECE");
		}
		if (departmentId == 3) {
			addFaculty.setDepartment("EEE");
		}
		if (departmentId == 4) {
			addFaculty.setDepartment("IT");
		}
		if (departmentId == 5) {
			addFaculty.setDepartment("MECH");
		}
		if (adminService.addFacultyDetails(addFaculty)) {
			LOGGER.info(DisplayConstants.ADD_SUCCESS);
		} else {
			System.err.println(DisplayConstants.ADD_ERROR);
		}

	}

	public void addSubject() {
		SubjectData addSubject = new SubjectData();
		addSubject.setSubjectCode(this.scanInput(DisplayConstants.ENTER_SUBJECT_CODE,
				RegexConstants.ALPHA_NUMERIC_REGEX, DisplayConstants.INVALID_INPUT));
		addSubject.setSubjectName(this.scanInput(DisplayConstants.ENTER_SUBJECT_NAME, RegexConstants.ALPHA_REGEX,
				DisplayConstants.INVALID_INPUT));
		if (adminService.addSubjectDetails(addSubject)) {
			LOGGER.info(DisplayConstants.ADD_SUCCESS);
		} else {
			System.err.println(DisplayConstants.ADD_ERROR);
		}
	}

	public void addCollege() {
		CollegeData addCollege = new CollegeData();
		addCollege.setCollegeCode(Integer.parseInt(this.scanInput(DisplayConstants.ENTER_COLLEGE_CODE,
				RegexConstants.INT_REGEX, DisplayConstants.INVALID_INPUT)));
		addCollege.setCollegeName(this.scanInput(DisplayConstants.ENTER_COLLEGE_NAME, RegexConstants.COLLEGE_CODE_REGEX,
				DisplayConstants.INVALID_INPUT));
		if (adminService.addCollegeDetails(addCollege)) {
			LOGGER.info(DisplayConstants.ADD_SUCCESS);
		} else {
			System.err.println(DisplayConstants.ADD_ERROR);
		}
	}

	public void addResult() {
		int gradeId = 0;
		String grade = "";
		ResultData addResult = new ResultData();
		addResult.setStudentRegistrationNumber(Long.parseLong(this.scanInput(DisplayConstants.ENTER_REGNO,
				RegexConstants.INT_REGEX, DisplayConstants.INVALID_INPUT)));
		addResult.setSubjectCode(this.scanInput(DisplayConstants.ENTER_SUBJECT_CODE, RegexConstants.ALPHA_NUMERIC_REGEX,
				DisplayConstants.INVALID_INPUT));
		gradeId = Integer.parseInt(this.scanInput(DisplayConstants.GRADE_LIST, RegexConstants.GRADE_REGEX,
				DisplayConstants.INVALID_INPUT));
		if (gradeId == 1) {
			grade = "S";
		} else if (gradeId == 2) {
			grade = "A";
		} else if (gradeId == 3) {
			grade = "B";
		} else if (gradeId == 4) {
			grade = "C";
		} else if (gradeId == 5) {
			grade = "D";
		} else if (gradeId == 6) {
			grade = "E";
		} else if (gradeId == 7) {
			grade = "U";
		}
		addResult.setGrade(grade);
		
		addResult.setWrittenIn(Integer.parseInt(
				this.scanInput(DisplayConstants.ENTER_SEMESTER_WRITTEN,RegexConstants.SEMESTER_REGEX, DisplayConstants.INVALID_INPUT)));
		if (adminService.addResults(addResult)) {
			LOGGER.info(DisplayConstants.ADD_SUCCESS);
		} else {
			System.err.println(DisplayConstants.ADD_ERROR);
		}
	}

	public void addRevaluationResult() {
		int revaluationId = 0, gradeId;
		char grade = '\0';
		print(adminService.getApprovedRevaluationList());
		revaluationId = Integer.parseInt(this.scanInput(DisplayConstants.REVALUATION_ID, RegexConstants.INT_REGEX,
				DisplayConstants.INVALID_INPUT));
		gradeId = Integer.parseInt(this.scanInput(DisplayConstants.GRADE_LIST, RegexConstants.GRADE_REGEX,
				DisplayConstants.INVALID_INPUT));
		if (gradeId == 1) {
			grade = 'S';
		} else if (gradeId == 2) {
			grade = 'A';
		} else if (gradeId == 3) {
			grade = 'B';
		} else if (gradeId == 4) {
			grade = 'C';
		} else if (gradeId == 5) {
			grade = 'D';
		} else if (gradeId == 6) {
			grade = 'E';
		} else if (gradeId == 7) {
			grade = 'U';
		}

		if (adminService.updateGrade(revaluationId, grade)) {
			LOGGER.info(DisplayConstants.UPDATE_SUCCESS);
		} else {
			System.err.println(DisplayConstants.UPDATE_ERROR);
		}
	}

	public static boolean print(ArrayList<LinkedHashMap<String, String>> result) {
		if (result.isEmpty()) {
			System.err.println(DisplayConstants.NO_RECORDS);
			return false;
		}
		for (LinkedHashMap<String, String> map : result) {
			LOGGER.info(DisplayConstants.STAR);
			for (Entry<String, String> pair : map.entrySet()) {
				LOGGER.info(pair.getKey() + pair.getValue());
			}
		}
		LOGGER.info(DisplayConstants.STAR);
		return true;
	}

	public void deleteStudent() {
		if (print(adminService.getAllStudentDetails())) {
			long registrationNumber = Long.parseLong(this.scanInput(DisplayConstants.ENTER_REGNO,
					RegexConstants.INT_REGEX, DisplayConstants.INVALID_INPUT));
			if (adminService.deleteStudentDetails(registrationNumber)) {
				LOGGER.info(DisplayConstants.DELETE_SUCCESS);
			} else {
				System.err.println(DisplayConstants.DELETE_ERROR);
			}
		}
	}

	public void deleteFaculty() {
		if (print(adminService.getAllFacultyDetails())) {
			long registrationNumber = Long.parseLong(this.scanInput(DisplayConstants.ENTER_REGNO,
					RegexConstants.INT_REGEX, DisplayConstants.INVALID_INPUT));
			if (adminService.deleteFacultyDetails(registrationNumber)) {
				LOGGER.info(DisplayConstants.DELETE_SUCCESS);
			} else {
				System.err.println(DisplayConstants.DELETE_ERROR);
			}
		}
	}

	public void deleteSubject() {
		if (print(adminService.getSubjectDetailsById(0, 0))) {
			String subjectCode = this.scanInput(DisplayConstants.ENTER_REGNO, RegexConstants.ALPHA_NUMERIC_REGEX,
					DisplayConstants.INVALID_INPUT);
			if (adminService.deleteSubjectDetails(subjectCode)) {
				LOGGER.info(DisplayConstants.DELETE_SUCCESS);
			} else {
				System.err.println(DisplayConstants.DELETE_ERROR);
			}
		}
	}

	public void deleteCollege() {
		if (print(adminService.getAllCollegeDetails())) {
			int collegeCode = Integer.parseInt(this.scanInput(DisplayConstants.ENTER_COLLEGE_CODE,
					RegexConstants.COLLEGE_CODE_REGEX, DisplayConstants.INVALID_INPUT));
			if (adminService.deleteCollegeDetails(collegeCode)) {
				LOGGER.info(DisplayConstants.DELETE_SUCCESS);
			} else {
				System.err.println(DisplayConstants.DELETE_ERROR);
			}
		}
	}

	public void updateStudent() {
		String dataToupdate = "";
		int columnId = 0, flag = 0,departmentId=0;
		if (print(adminService.getAllStudentDetails())) {
			long registrationNumber = Long.parseLong(this.scanInput(DisplayConstants.ENTER_REGNO,
					RegexConstants.INT_REGEX, DisplayConstants.INVALID_INPUT));
			columnId = Integer.parseInt(this.scanInput(DisplayConstants.UPDATE_STUDENT_COLUMN,
					RegexConstants.EDIT_MENU_REGEX, DisplayConstants.INVALID_INPUT));
			if (columnId == 1) {
				dataToupdate= this.scanInput(DisplayConstants.ENTER_DATA_TO_UPDATE, RegexConstants.ALPHA_REGEX,
						DisplayConstants.INVALID_INPUT);

			} else if (columnId == 2) {
				flag = 1;
				dataToupdate = this.scanInput(DisplayConstants.ENTER_DATA_TO_UPDATE,
						RegexConstants.COLLEGE_CODE_REGEX, DisplayConstants.INVALID_INPUT);
			} else if (columnId == 3) {
				departmentId=Integer.parseInt(this.scanInput(DisplayConstants.ENTER_DEPARTMENT,RegexConstants.DEPT_REGEX , DisplayConstants.INVALID_INPUT));
				if (departmentId == 1) {
					dataToupdate ="CSE";
				}
				else if (departmentId == 2) {
					dataToupdate ="ECE";
				}
				else if (departmentId == 3) {
					dataToupdate ="EEE";
				}
				else if (departmentId == 4) {
					dataToupdate ="IT";
				}
				else if (departmentId == 5) {
					dataToupdate ="MECH";
				}
			} else if (columnId == 4) {
				flag = 1;
				dataToupdate = this.scanInput(DisplayConstants.ENTER_DATA_TO_UPDATE, RegexConstants.SEMESTER_REGEX,
						DisplayConstants.INVALID_INPUT);
			}
			if (adminService.updateStudentDetails(registrationNumber, dataToupdate, columnId, flag)) {
				LOGGER.info(DisplayConstants.UPDATE_SUCCESS);
			} else {
				System.err.println(DisplayConstants.UPDATE_ERROR);
			}
		}
	}

	public void updateFaculty() {
		String dataToUpdate = "";int departmentId=0;
		int columnId = 0, flag = 0;
		if (print(adminService.getAllFacultyDetails())) {
			long registrationNumber = Long.parseLong(this.scanInput(DisplayConstants.ENTER_REGNO,
					RegexConstants.INT_REGEX, DisplayConstants.INVALID_INPUT));
			columnId = Integer.parseInt(
					this.scanInput(DisplayConstants.UPDATE_FACULTY_COLUMN, "[1-3]", DisplayConstants.INVALID_INPUT));
			if (columnId == 1) {
				dataToUpdate = this.scanInput(DisplayConstants.ENTER_DATA_TO_UPDATE, RegexConstants.ALPHA_REGEX,
						DisplayConstants.INVALID_INPUT);
			} else if (columnId == 2) {
				flag = 1;
				dataToUpdate = this.scanInput(DisplayConstants.ENTER_DATA_TO_UPDATE,
						RegexConstants.COLLEGE_CODE_REGEX, DisplayConstants.INVALID_INPUT);
			} else if (columnId == 3) {
				departmentId=Integer.parseInt(this.scanInput(DisplayConstants.ENTER_DEPARTMENT,RegexConstants.DEPT_REGEX , DisplayConstants.INVALID_INPUT));
				if (departmentId == 1) {
					dataToUpdate ="CSE";
				}
				else if (departmentId == 2) {
					dataToUpdate ="ECE";
				}
				else if (departmentId == 3) {
					dataToUpdate ="EEE";
				}
				else if (departmentId == 4) {
					dataToUpdate ="IT";
				}
				else if (departmentId == 5) {
					dataToUpdate ="MECH";
				}
			}
			if (adminService.updateFacultyDetails(registrationNumber, dataToUpdate, columnId, flag)) {
				LOGGER.info(DisplayConstants.UPDATE_SUCCESS);
			} else {
				System.err.println(DisplayConstants.UPDATE_ERROR);
			}
		}
	}

	public void editStudentMenu() {
		do {
			option = Integer.parseInt(this.scanInput(DisplayConstants.EDITSTUDENT_MENU, RegexConstants.EDIT_MENU_REGEX,
					DisplayConstants.INVALID_INPUT));
			switch (option) {
			case 1:
				this.addStudent();
				break;
			case 2:
				this.deleteStudent();
				break;
			case 3:
				this.updateStudent();
				break;
			case 4:
				break;
			}
		} while (option != 4);
	}

	public void editFacultyMenu() {
		do {
			option = Integer.parseInt(this.scanInput(DisplayConstants.EDITFACULTY_MENU, RegexConstants.EDIT_MENU_REGEX,
					DisplayConstants.INVALID_INPUT));
			switch (option) {
			case 1:
				this.addFaculty();
				break;
			case 2:
				this.deleteFaculty();
				break;
			case 3:
				this.updateFaculty();
				break;
			case 4:
				break;
			}
		} while (option != 4);
	}

	public void editSubjectMenu() {
		do {
			option = Integer.parseInt(
					this.scanInput(DisplayConstants.EDITSUBJECT_MENU, "[1-3]", DisplayConstants.INVALID_INPUT));
			switch (option) {
			case 1:
				this.addSubject();
				break;
			case 2:
				this.deleteSubject();
				break;
			case 3:
				break;
			}
		} while (option != 3);
	}

	public void editCollegeMenu() {
		do {
			option = Integer.parseInt(
					this.scanInput(DisplayConstants.EDITCOLLEGE_MENU, "[1-3]", DisplayConstants.INVALID_INPUT));
			switch (option) {
			case 1:
				this.addCollege();
				break;
			case 2:
				this.deleteCollege();
				break;
			case 3:
				break;
			}
		} while (option != 3);
	}

	public void adminMenu() {
		LOGGER.info("ADMIN PORTAL: WELCOME ADMIN");
		do {
			option = Integer
					.parseInt(this.scanInput(DisplayConstants.ADMIN_MENU, "[1-7]", DisplayConstants.INVALID_INPUT));
			switch (option) {
			case 1:
				this.editStudentMenu();
				break;
			case 2:
				this.editFacultyMenu();
				break;
			case 3:
				this.editSubjectMenu();
				break;
			case 4:
				this.editCollegeMenu();
				break;
			case 5:
				this.addResult();
				break;
			case 6:
				this.addRevaluationResult();
				break;
			case 7:
				break;
			}
		} while (option != 7);
	}
}
