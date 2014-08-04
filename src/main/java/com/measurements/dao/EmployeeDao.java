package com.measurements.dao;

import java.util.List;

import com.measurements.model.Employee;

public interface EmployeeDao {
	List<Employee> getAllByName(String name);
	void save(Employee employee);
}
