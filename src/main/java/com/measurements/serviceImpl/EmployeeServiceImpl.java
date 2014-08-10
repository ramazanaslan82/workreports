package com.measurements.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

import com.measurements.dao.EmployeeDao;
import com.measurements.model.Employee;
import com.measurements.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	private static List<String> names = new ArrayList<String>();

	public EmployeeServiceImpl() {
		super();
		buildWorkers();
	}

	private void buildWorkers() {
		names.clear();
		List<Employee> workers = getAllWorkers();
		CollectionUtils.forAllDo(workers, new Closure() {
			public void execute(Object arg0) {
				Employee worker = (Employee) arg0;
				names.add(worker.getName());
			}
		});
	}

	private List<Employee> getAllWorkers() {
		List<Employee> workers = new ArrayList<Employee>();
		workers.add(new Employee("Enver"));
		workers.add(new Employee("İlker"));
		workers.add(new Employee("Yaşar"));
		workers.add(new Employee("Yusuf"));
		workers.add(new Employee("Samet"));
		workers.add(new Employee("Zahit"));
		workers.add(new Employee("Coşkun"));
		workers.add(new Employee("Bülent"));
		workers.add(new Employee("Kadir"));
		workers.add(new Employee("Ali"));
		workers.add(new Employee("Gökhan"));
		workers.add(new Employee("Cevdet"));
		workers.add(new Employee("Alper"));
		workers.add(new Employee("Figen"));
		return workers;
	}

	public List<String> getWorkerNames() {
		return names;
	}

	public Employee getByName(String name) {
		if (names.contains(name))
		{
			return new Employee(name);
		}
		else
		{
			return new Employee("unknown_"+name);
		}
	}

	
	public List<Employee> getAllByNames(List<String> employeeNames)
	{
		List<Employee> employees = new ArrayList<Employee>();
		for(String employeeName:employeeNames)
		{
			employees.add(getByName(employeeName));
		}
		return employees;
	}
	
	@Transactional
	public void saveEmployeeByName(String name)
	{
		if(null == employeeDao)
		{
			System.out.println("employeeDao is null");
			return;
		}
		
		List<Employee> list = employeeDao.getAllByName(name);
		if(list.size()>0)
		{
			return;
		}
		Employee employee = new Employee(name);
		employeeDao.save(employee);
	}
}
