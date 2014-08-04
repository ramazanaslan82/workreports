package com.measurements.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.measurements.dao.EmployeeDao;
import com.measurements.model.Employee;

public class EmployeeHibernateDao implements EmployeeDao {
	private SessionFactory sessionFactory;

	private Employee loadEmployee(Long id) {

		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Employee> getAllByName(String name)
	{
		Session currentSession = getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();
		Criteria criteria = currentSession.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("name", name));
		List<Employee> list = (List<Employee>) criteria.list();
		currentSession.getTransaction().commit();
		return list;
	}
	
	public void save(Employee employee)
	{
		Session currentSession = getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();
		currentSession.save(employee);
		currentSession.getTransaction().commit();
	}
}
