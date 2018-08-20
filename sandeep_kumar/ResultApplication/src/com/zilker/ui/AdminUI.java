package com.zilker.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.Map.Entry;

import com.zilker.delegates.Validator;
import com.zilker.services.AdminServices;
import com.zilker.beans.*;
import com.zilker.constants.StringConstants;
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
				LOGGER.info(errorDisplay);
			}
		} while (proceed);
		return getInput;
	}

	public void addStudent() {
		int departmentId = 0;
		StudentData addStudent = new StudentData();
		addStudent.setStudentRegistrationNumber(Long.parseLong(this.scanInput(StringConstants.ENTER_REGNO,
				RegexConstants.NUMERIC_REGEX, StringConstants.INVALID_INPUT)));
		addStudent.setName(this.scanInput(StringConstants.ENTER_NAME, RegexConstants.ALPHA_REGEX,
				StringConstants.INVALID_INPUT));
		addStudent.setCollegeCode(this.scanInput(StringConstants.ENTER_COLLEGE_CODE, RegexConstants.COLLEGE_CODE_REGEX,
				StringConstants.INVALID_INPUT));
		departmentId = Integer
				.parseInt(this.scanInput(StringConstants.ENTER_DEPARTMENT, "[1-5]+", StringConstants.INVALID_INPUT));
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
		addStudent.setSemester(Integer.parseInt(this.scanInput(StringConstants.ENTER_SEMESTER,
				RegexConstants.SEMESTER_REGEX, StringConstants.INVALID_INPUT)));
		if (adminService.addStudentDetails(addStudent)) {
			LOGGER.info(StringConstants.ADD_SUCCESS);
		} else {
			LOGGER.info(StringConstants.ADD_ERROR);
		}
	}

	public void addFaculty() {
		int departmentId = 0;
		FacultyData addFaculty = new FacultyData();
		addFaculty.setFacultyRegistrationNumber(Long.parseLong(this.scanInput(StringConstants.ENTER_REGNO,
				RegexConstants.NUMERIC_REGEX, StringConstants.INVALID_INPUT)));
		addFaculty.setName(this.scanInput(StringConstants.ENTER_NAME, RegexConstants.ALPHA_REGEX,
				StringConstants.INVALID_INPUT));
		addFaculty.setCollegeCode(this.scanInput(StringConstants.ENTER_COLLEGE_CODE, RegexConstants.COLLEGE_CODE_REGEX,
				StringConstants.INVALID_INPUT));
		departmentId = Integer.parseInt(this.scanInput(StringConstants.ENTER_DEPARTMENT, RegexConstants.DEPT_REGEX,
				StringConstants.INVALID_INPUT));
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
			LOGGER.info(StringConstants.ADD_SUCCESS);
		} else {
			LOGGER.info(StringConstants.ADD_ERROR);
		}

	}

	public void addSubject() {
		SubjectData addSubject = new SubjectData();
		addSubject.setSubjectCode(this.scanInput(StringConstants.ENTER_SUBJECT_CODE,
				RegexConstants.ALPHA_NUMERIC_REGEX, StringConstants.INVALID_INPUT));
		addSubject.setSubjectName(this.scanInput(StringConstants.ENTER_SUBJECT_NAME, RegexConstants.ALPHA_REGEX,
				StringConstants.INVALID_INPUT));
		if (adminService.addSubjectDetails(addSubject)) {
			LOGGER.info(StringConstants.ADD_SUCCESS);
		} else {
			LOGGER.info(StringConstants.ADD_ERROR);
		}
	}

	public void addCollege() {
		CollegeData addCollege = new CollegeData();
		addCollege.setCollegeCode(Integer.parseInt(this.scanInput(StringConstants.ENTER_COLLEGE_CODE,
				RegexConstants.NUMERIC_REGEX, StringConstants.INVALID_INPUT)));
		addCollege.setCollegeName(this.scanInput(StringConstants.ENTER_COLLEGE_NAME, RegexConstants.COLLEGE_CODE_REGEX,
				StringConstants.INVALID_INPUT));
		if (adminService.addCollegeDetails(addCollege)) {
			LOGGER.info(StringConstants.ADD_SUCCESS);
		} else {
			LOGGER.info(StringConstants.ADD_ERROR);
		}
	}

	public void addResult() {
		int gradeId = 0;
		String grade = "";
		ResultData addResult = new ResultData();
		addResult.setStudentRegistrationNumber(Long.parseLong(this.scanInput(StringConstants.ENTER_REGNO,
				RegexConstants.NUMERIC_REGEX, StringConstants.INVALID_INPUT)));
		addResult.setSubjectCode(this.scanInput(StringConstants.ENTER_SUBJECT_CODE, RegexConstants.ALPHA_NUMERIC_REGEX,
				StringConstants.INVALID_INPUT));
		gradeId = Integer.parseInt(this.scanInput(StringConstants.GRADE_LIST, RegexConstants.GRADE_REGEX,
				StringConstants.INVALID_INPUT));
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
				this.scanInput(StringConstants.ENTER_SEMESTER_WRITTEN,RegexConstants.SEMESTER_REGEX, StringConstants.INVALID_INPUT)));
		if (adminService.addResults(addResult)) {
			LOGGER.info(StringConstants.ADD_SUCCESS);
		} else {
			LOGGER.info(StringConstants.ADD_ERROR);
		}
	}

	public void addRevaluationResult() {
		int revaluationId = 0, gradeId;
		char grade = '\0';
		print(adminService.getApprovedRevaluationList());
		revaluationId = Integer.parseInt(this.scanInput(StringConstants.REVALUATION_ID, RegexConstants.NUMERIC_REGEX,
				StringConstants.INVALID_INPUT));
		gradeId = Integer.parseInt(this.scanInput(StringConstants.GRADE_LIST, RegexConstants.GRADE_REGEX,
				StringConstants.INVALID_INPUT));
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
			LOGGER.info(StringConstants.UPDATE_SUCCESS);
		} else {
			LOGGER.info(StringConstants.UPDATE_ERROR);
		}
	}

	public static boolean print(ArrayList<LinkedHashMap<String, String>> result) {
		if (result.isEmpty()) {
			LOGGER.info(StringConstants.NO_RECORDS);
			return false;
		}
		for (LinkedHashMap<String, String> map : result) {
			LOGGER.info(StringConstants.STAR);
			for (Entry<String, String> pair : map.entrySet()) {
				LOGGER.info(pair.getKey() + pair.getValue());
			}
		}
		LOGGER.info(StringConstants.STAR);
		return true;
	}

	public void deleteStudent() {
		if (print(adminService.getAllStudentDetails())) {
			long registrationNumber = Long.parseLong(this.scanInput(StringConstants.ENTER_REGNO,
					RegexConstants.NUMERIC_REGEX, StringConstants.INVALID_INPUT));
			if (adminService.deleteStudentDetails(registrationNumber)) {
				LOGGER.info(StringConstants.DELETE_SUCCESS);
			} else {
				LOGGER.info(StringConstants.DELETE_ERROR);
			}
		}
	}

	public void deleteFaculty() {
		if (print(adminService.getAllFacultyDetails())) {
			long registrationNumber = Long.parseLong(this.scanInput(StringConstants.ENTER_REGNO,
					RegexConstants.NUMERIC_REGEX, StringConstants.INVALID_INPUT));
			if (adminService.deleteFacultyDetails(registrationNumber)) {
				LOGGER.info(StringConstants.DELETE_SUCCESS);
			} else {
				LOGGER.info(StringConstants.DELETE_ERROR);
			}
		}
	}

	public void deleteSubject() {
		if (print(adminService.getSubjectDetailsById(0, 0))) {
			String subjectCode = this.scanInput(StringConstants.ENTER_REGNO, RegexConstants.ALPHA_NUMERIC_REGEX,
					StringConstants.INVALID_INPUT);
			if (adminService.deleteSubjectDetails(subjectCode)) {
				LOGGER.info(StringConstants.DELETE_SUCCESS);
			} else {
				LOGGER.info(StringConstants.DELETE_ERROR);
			}
		}
	}

	public void deleteCollege() {
		if (print(adminService.getAllCollegeDetails())) {
			int collegeCode = Integer.parseInt(this.scanInput(StringConstants.ENTER_COLLEGE_CODE,
					RegexConstants.COLLEGE_CODE_REGEX, StringConstants.INVALID_INPUT));
			if (adminService.deleteCollegeDetails(collegeCode)) {
				LOGGER.info(StringConstants.DELETE_SUCCESS);
			} else {
				LOGGER.info(StringConstants.DELETE_ERROR);
			}
		}
	}

	public void updateStudent() {
		String dataToupdate = "";
		int columnId = 0, flag = 0,departmentId=0;
		if (print(adminService.getAllStudentDetails())) {
			long registrationNumber = Long.parseLong(this.scanInput(StringConstants.ENTER_REGNO,
					RegexConstants.NUMERIC_REGEX, StringConstants.INVALID_INPUT));
			columnId = Integer.parseInt(this.scanInput(StringConstants.UPDATE_STUDENT_COLUMN,
					RegexConstants.EDIT_MENU_REGEX, StringConstants.INVALID_INPUT));
			if (columnId == 1) {
				dataToupdate= this.scanInput(StringConstants.ENTER_DATA_TO_UPDATE, RegexConstants.ALPHA_REGEX,
						StringConstants.INVALID_INPUT);

			} else if (columnId == 2) {
				flag = 1;
				dataToupdate = this.scanInput(StringConstants.ENTER_DATA_TO_UPDATE,
						RegexConstants.COLLEGE_CODE_REGEX, StringConstants.INVALID_INPUT);
			} else if (columnId == 3) {
				departmentId=Integer.parseInt(this.scanInput(StringConstants.ENTER_DEPARTMENT,RegexConstants.DEPT_REGEX , StringConstants.INVALID_INPUT));
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
				dataToupdate = this.scanInput(StringConstants.ENTER_DATA_TO_UPDATE, RegexConstants.SEMESTER_REGEX,
						StringConstants.INVALID_INPUT);
			}
			if (adminService.updateStudentDetails(registrationNumber, dataToupdate, columnId, flag)) {
				LOGGER.info(StringConstants.UPDATE_SUCCESS);
			} else {
				LOGGER.info(StringConstants.UPDATE_ERROR);
			}
		}
	}

	public void updateFaculty() {
		String dataToUpdate = "";int departmentId=0;
		int columnId = 0, flag = 0;
		if (print(adminService.getAllFacultyDetails())) {
			long registrationNumber = Long.parseLong(this.scanInput(StringConstants.ENTER_REGNO,
					RegexConstants.NUMERIC_REGEX, StringConstants.INVALID_INPUT));
			columnId = Integer.parseInt(
					this.scanInput(StringConstants.UPDATE_FACULTY_COLUMN, "[1-3]", StringConstants.INVALID_INPUT));
			if (columnId == 1) {
				dataToUpdate = this.scanInput(StringConstants.ENTER_DATA_TO_UPDATE, RegexConstants.ALPHA_REGEX,
						StringConstants.INVALID_INPUT);
			} else if (columnId == 2) {
				flag = 1;
				dataToUpdate = this.scanInput(StringConstants.ENTER_DATA_TO_UPDATE,
						RegexConstants.COLLEGE_CODE_REGEX, StringConstants.INVALID_INPUT);
			} else if (columnId == 3) {
				departmentId=Integer.parseInt(this.scanInput(StringConstants.ENTER_DEPARTMENT,RegexConstants.DEPT_REGEX , StringConstants.INVALID_INPUT));
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
				LOGGER.info(StringConstants.UPDATE_SUCCESS);
			} else {
				LOGGER.info(StringConstants.UPDATE_ERROR);
			}
		}
	}

	public void editStudentMenu() {
		do {
			option = Integer.parseInt(this.scanInput(StringConstants.EDITSTUDENT_MENU, RegexConstants.EDIT_MENU_REGEX,
					StringConstants.INVALID_INPUT));
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
			option = Integer.parseInt(this.scanInput(StringConstants.EDITFACULTY_MENU, RegexConstants.EDIT_MENU_REGEX,
					StringConstants.INVALID_INPUT));
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
					this.scanInput(StringConstants.EDITSUBJECT_MENU, "[1-3]", StringConstants.INVALID_INPUT));
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
					this.scanInput(StringConstants.EDITCOLLEGE_MENU, "[1-3]", StringConstants.INVALID_INPUT));
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
					.parseInt(this.scanInput(StringConstants.ADMIN_MENU, "[1-7]", StringConstants.INVALID_INPUT));
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
