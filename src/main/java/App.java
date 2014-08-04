import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.measurements.model.Employee;
import com.measurements.model.Event;
import com.measurements.model.WorkLog;
import com.measurements.service.EmployeeService;
import com.measurements.service.ReaderService;

public class App {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		
		ReaderService reader = context.getBean(ReaderService.class);
		EmployeeService employeeService = context.getBean(EmployeeService.class);
		List<WorkLog> workLogs = reader.readFile();
		for(WorkLog workLog : workLogs)
		{
			Date day = workLog.getDay();
			List<Event> events = workLog.getEvents();
			for(Event event : events)
			{
				List<Employee> workers = event.getWorkers();
				for(Employee employee : workers)
				{
					employeeService.saveEmployeeByName(employee.getName());
				}
			}
		}
		
		
	}
}
