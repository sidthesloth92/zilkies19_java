package io.ztech.delegates;

import org.springframework.web.servlet.ModelAndView;

import io.ztech.dao.EmployeeDao;
import io.ztech.employee.Employee;

public class EmployeeDelegate {
	public ModelAndView getTable() {
		EmployeeDao daoObj = new EmployeeDao();
		Employee[] employees = daoObj.getFromTable();
		Employee[] tableOrder = new Employee[employees.length];
		int index = 0;
		for(int i=0; i<employees.length; i++) {
			if(employees[i].getManager().trim().equals("")) {
				tableOrder[index++] = employees[i];
			}
		}
		for(int i=0; i<index; i++) {
			for(int j=0; j<employees.length; j++) {
				if(employees[j].getManager().equals(tableOrder[i].getName())) {
					tableOrder[index++] = employees[j];
				}
			}
		}
		
		ModelAndView model = new ModelAndView("table");
		model.addObject("empList", tableOrder);
		return model;
	}
	
	public String validateForm(String fullName, String designation, String manager) {
		EmployeeDao daoObj = new EmployeeDao();
		daoObj.insertIntoTable(fullName, designation, manager);
		return "../../index";
	}
}
