package com.management.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.management.beans.Department;

@Repository
public class DepartmentDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Department> getDepartments() {
		System.out.println("Fetching all departments");
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Department.class);
		return criteria.list();
	}

	@SuppressWarnings("deprecation")
	public Department getDepartmentById(long id) {
		System.out.println("Fetching department for department id " + id);
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Department.class);
		criteria.add(Restrictions.eq("id", id));
		return (Department) criteria.uniqueResult();
	}

	public Department editDepartment(Department d) {
		System.out.println("Editing department for department id " + d.getDepId());
		Department d1 = new Department();
		try {
			Transaction tx = entityManager.unwrap(Session.class).beginTransaction();
			d1.setDepId(d.getDepId());
			d1.setDepName(d.getDepName());
			entityManager.unwrap(Session.class).saveOrUpdate(d1);
			tx.commit();
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			entityManager.unwrap(Session.class).close();
		}
		return d1;
	}

	public String addDepartment(Department d) {
		System.out.println("Adding department to the database");
		Department d1 = new Department();
		String response = "";
		try {
			Transaction tx = entityManager.unwrap(Session.class).beginTransaction();
			d1.setDepId(d.getDepId());
			d1.setDepName(d.getDepName());
			entityManager.unwrap(Session.class).save(d1);
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

	public String deleteDepartment(long id) {
		System.out.println("Deleting record for department id " + id);
		String response = "";
		try {
			Transaction tx = entityManager.unwrap(Session.class).beginTransaction();
			Department d = (Department) (entityManager.unwrap(Session.class)
					.createQuery("from Department d where id = " + id)).uniqueResult();
			entityManager.unwrap(Session.class).delete(d);
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
