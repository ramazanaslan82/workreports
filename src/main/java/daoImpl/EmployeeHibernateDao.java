package daoImpl;

import model.Employee;

import org.hibernate.SessionFactory;

import dao.EmployeeDao;

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
}
