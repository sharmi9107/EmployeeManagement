package com.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.beans.Department;
import com.management.dao.DepartmentDAO;

@RestController
public class DeptController {
	@Autowired
	private DepartmentDAO depDao;

	@RequestMapping("/listDepartments")
	public List<Department> listDepartments() {
		return depDao.getDepartments();
	}

	@RequestMapping("/fetchDepartment")
	public Department fetchDepartment(@RequestParam(value = "id", required = false) Long id) throws Exception {
		if (id == null) {
			throw new java.lang.Exception();
		} else {
			return depDao.getDepartmentById(id);
		}
	}

	@RequestMapping(value = "editDepartment", method = RequestMethod.POST)
	public Department editDepartment(@RequestBody Department d) throws Exception {
		if (d != null) {
			return depDao.editDepartment(d);
		} else
			throw new java.lang.Exception();
	}

	// TO DO -- add post
	@RequestMapping(value = "addDepartment", method = RequestMethod.POST)
	public String addDepartment(@RequestBody Department d) throws Exception {
		if (d != null) {
			return depDao.addDepartment(d);
		} else
			return "Addition unsuccessful";
	}

	@RequestMapping("/deleteDepartment")
	public String deleteDepartment(@RequestParam(value = "id", required = false) Long id) throws Exception {
		if (id != null) {
			return depDao.deleteDepartment(id);
		} else
			return "Deletion unsuccessful";
	}

}
