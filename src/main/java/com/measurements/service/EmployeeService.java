package com.measurements.service;

import java.util.List;

public interface EmployeeService {
	List<String> getWorkerNames();
	void saveEmployeeByName(String name);
}
