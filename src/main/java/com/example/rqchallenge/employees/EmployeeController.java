package com.example.rqchallenge.employees;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.restapiconnection.RestApiConnection;

@Component
public class EmployeeController implements IEmployeeController {

	@Override
	public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
		List<Employee> employees = new RestApiConnection().getEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {

		List<Employee> employees = new RestApiConnection().getEmployees();
		List<Employee> employeeSearch = new ArrayList<Employee>();

		for (Employee e : employees) {
			if (e.getEmplyeeName().contains(searchString)) {
				employeeSearch.add(e);
			}
		}
		return new ResponseEntity<List<Employee>>(employeeSearch, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> getEmployeeById(String id) {
		Employee employee = new RestApiConnection().getEmployee(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> getHighestSalaryOfEmployees() {

		List<Employee> employees = new RestApiConnection().getEmployees();

		Integer highest = employees.get(0).getEmployeeSalary();
		for (Employee e : employees) {
			if (highest < e.getEmployeeSalary()) {
				highest = e.getEmployeeSalary();
			}
		}
		return new ResponseEntity<Integer>(highest, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {

		List<Employee> employees = new RestApiConnection().getEmployees();

		PriorityQueue<Employee> pq = new PriorityQueue<Employee>(new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getEmployeeSalary() - o2.getEmployeeSalary();
			}

		});

		for (Employee e : employees) {
			pq.add(e);
			if (pq.size() > 10) {
				pq.poll();
			}
		}

		List<String> topTenEarners = new ArrayList<String>();
		while (!pq.isEmpty()) {
			topTenEarners.add(pq.poll().getEmplyeeName());
		}

		return new ResponseEntity<List<String>>(topTenEarners, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
		Employee employee = new RestApiConnection().createEmployee(employeeInput);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteEmployeeById(String id) {
		Employee employee = new RestApiConnection().getEmployee(id);
		new RestApiConnection().deleteEmployee(id);
		return new ResponseEntity<String>(employee.getEmplyeeName(), HttpStatus.OK);
	}

}
