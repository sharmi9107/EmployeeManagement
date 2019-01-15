package com.management.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {
	@Id
	// @GeneratedValue
	@Column(name = "department_id")
	private long depId;

	@Column(name = "department_name")
	private String depName;

	public Department() {

	}

	public Department(long id, String name) {
		this.depId = id;
		this.depName = name;
	}

	/**
	 * @return the depId
	 */
	public long getDepId() {
		return depId;
	}

	/**
	 * @param depId
	 *            the depId to set
	 */
	public void setDepId(long depId) {
		this.depId = depId;
	}

	/**
	 * @return the depName
	 */
	public String getDepName() {
		return depName;
	}

	/**
	 * @param depName
	 *            the depName to set
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}

}
