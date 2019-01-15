package com.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.beans.Employee;
import com.management.dao.EmployeeDAO;

@RestController
public class EmpController {

	@Autowired
	private EmployeeDAO empDao;

	@RequestMapping("/listEmployees")
	public List<Employee> listAllEmployees() {
		return empDao.getEmployees();
	}

	@RequestMapping("/fetchEmployee")
	public Employee fetchEmployee(@RequestParam(value = "id", required = false) Long id) throws Exception {
		if (id == null) {
			throw new java.lang.Exception();
		} else {
			return empDao.getEmployeeById(id);
		}
	}

	@RequestMapping(value = "editEmployee", method = RequestMethod.POST)
	public Employee editEmployee(@RequestBody Employee e) throws Exception {
		if (e != null) {
			return empDao.editEmployee(e);
		} else
			throw new java.lang.Exception();
	}

	// TO DO -- add post
	@RequestMapping(value = "addEmployee", method = RequestMethod.POST)
	public String addEmployee(@RequestBody Employee e) throws Exception {
		if (e != null) {
			return empDao.addEmployee(e);
		} else
			return "Addition unsuccessful";
	}

	@RequestMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam(value = "id", required = false) Long id) throws Exception {
		if (id != null) {
			return empDao.deleteEmployee(id);
		} else
			return "Deletion unsuccessful";
	}
}
