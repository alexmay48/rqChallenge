package com.example.rqchallenge.employees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.restapiconnection.RestApiConnection;

/**
 * This service layer holds the logic to for handling how we find the employees
 * as needed
 * 
 * @author alexmay
 *
 */
@Service
public class EmployeeService {

	@Autowired
	private RestApiConnection restApiConnection;

	/**
	 * 
	 * @return - All employees
	 */
	public List<Employee> getEmployees() {
		List<Employee> employees = restApiConnection.getEmployees();
		return employees;
	}

	/**
	 * 
	 * @param searchString - The string to search for in the name of each employee
	 * @return - all Employees whose name contains the searchString in their name
	 */
	public List<Employee> getEmployeeByNameSearch(String searchString) {
		List<Employee> employees = restApiConnection.getEmployees();
		List<Employee> employeeSearch = new ArrayList<Employee>();

		for (Employee e : employees) {
			if (e.getEmployeeName().contains(searchString)) {
				employeeSearch.add(e);
			}
		}
		return employeeSearch;
	}

	/**
	 * 
	 * @param id - The id of the employee to find
	 * @return - Employee with specified id
	 */
	public Employee getEmployee(String id) {
		Employee employee = restApiConnection.getEmployee(id);
		return employee;
	}

	/**
	 * 
	 * @return - The number of the highest salary
	 */
	public Integer getHighestSalaryOfEmployees() {
		List<Employee> employees = restApiConnection.getEmployees();

		Integer highest = employees.get(0).getEmployeeSalary();
		for (Employee e : employees) {
			if (highest < e.getEmployeeSalary()) {
				highest = e.getEmployeeSalary();
			}
		}
		return highest;
	}

	/**
	 * Searches through all employees to find the top ten earners. Uses a priority
	 * queue to keep the overall performance of the search as O(N log K), with N
	 * being all of the employees and K being 10.
	 * 
	 * @return - The list of the names of the top 10 earners
	 */
	public List<String> getTopTenHighestEarningEmployeeNames() {

		List<Employee> employees = restApiConnection.getEmployees();

		PriorityQueue<Employee> pq = new PriorityQueue<Employee>(new Comparator<Employee>() {
			// We want to make sure that we compare the employees by salary, and not by
			// other fields
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getEmployeeSalary() - o2.getEmployeeSalary();
			}
		});

		// Add employees to the priority queue, but keep the size only to 10. This will
		// keep the 10 top earners as pq.poll will remove the lowest earner.
		for (Employee e : employees) {
			pq.add(e);
			if (pq.size() > 10) {
				pq.poll();
			}
		}

		// Now take the employees in the Priority Queue and put their names in list
		List<String> topTenEarners = new ArrayList<String>();
		while (!pq.isEmpty()) {
			topTenEarners.add(pq.poll().getEmployeeName());
		}
		return topTenEarners;
	}

	/**
	 * Creates an employee in the service
	 * 
	 * @param employeeInput - The parameters passed in the URL
	 * @return - The information of the employee that was created in the service.
	 */
	public Employee createEmployee(Map<String, Object> employeeInput) {
		Employee employee = restApiConnection.createEmployee(employeeInput);
		return employee;
	}

	/**
	 * Deletes an employee in the service.
	 * 
	 * @param id - The id of the employee to delete
	 * @return - The employee info of the employee to remove.
	 */
	public Employee deleteEmployeeById(String id) {
		Employee employee = restApiConnection.getEmployee(id);
		restApiConnection.deleteEmployee(id);
		return employee;
	}

}
