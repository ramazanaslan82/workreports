package com.measurements.employees.dao;

import org.hibernate.SessionFactory;

import com.measurements.employees.Employee;

public class EmployeeHibernateDao {
	private SessionFactory sessionFactory;
	
	private Employee loadEmployee(Long id){
		
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
