package com.management.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.management.beans.Employee;

@SuppressWarnings("deprecation")
@Repository
public class EmployeeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked" })
	public List<Employee> getEmployees() {
		System.out.println("Fetching all employees");
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Employee.class);
		return criteria.list();
	}

	public Employee getEmployeeById(long id) {
		System.out.println("Fetching employee for employee id " + id);
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", id));
		return (Employee) criteria.uniqueResult();
	}

	public Employee editEmployee(Employee e) {
		System.out.println("Editing employee for employee id " + e.getEmpId());
		Employee e1 = new Employee();
		try {
			Transaction tx = entityManager.unwrap(Session.class).beginTransaction();
			e1.setEmpId(e.getEmpId());
			e1.setEmpName(e.getEmpName());
			e1.setDepartment_id(e.getDepartment_id());
			entityManager.unwrap(Session.class).saveOrUpdate(e1);
			tx.commit();
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			entityManager.unwrap(Session.class).close();
		}
		return e1;
	}

	public String addEmployee(Employee e) {
		System.out.println("Adding employee to the database");
		String response = "";
		Employee e1 = new Employee();
		try {
			Transaction tx = entityManager.unwrap(Session.class).beginTransaction();
			e1.setEmpId(e.getEmpId());
			e1.setEmpName(e.getEmpName());
			e1.setDepartment_id(e.getDepartment_id());
			entityManager.unwrap(Session.class).save(e1);
			tx.commit();
			response = "Addition successful";
		} catch (Exception exp) {
			exp.printStackTrace();
			response = "Addition unsuccessful";
		} finally {
			entityManager.unwrap(Session.class).close();
		}
		return response;
	}

	public String deleteEmployee(long id) {
		System.out.println("Deleting record for employee id " + id);
		String response = "";
		try {
			Transaction tx = entityManager.unwrap(Session.class).beginTransaction();
			Employee e = (Employee) (entityManager.unwrap(Session.class)
					.createQuery("from Employee e where id = " + id)).uniqueResult();
			entityManager.unwrap(Session.class).delete(e);
			tx.commit();
			response = "Deletion successful";
		} catch (Exception e) {
			e.printStackTrace();
			response = "Deletion unsuccessful";
		} finally {
			entityManager.unwrap(Session.class).close();
		}
		return response;
	}

}
