package com.management.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	@Id
//	@GeneratedValue
	@Column (name = "employee_id")
	private long empId;

	@Column (name = "employee_name")
	private String empName;

	@ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	private Department department;

	public Employee() {

	}

	public Employee(long id, String name, Department department_id) {
		super();
		this.empId = id;
		this.empName = name;
		this.department = department_id;
	}

	/**
	 * @return the empId
	 */
	public long getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(long empId) {
		this.empId = empId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the department_id
	 */
	public Department getDepartment_id() {
		return department;
	}

	/**
	 * @param department_id
	 *            the department_id to set
	 */
	public void setDepartment_id(Department department_id) {
		this.department = department_id;
	}

}
