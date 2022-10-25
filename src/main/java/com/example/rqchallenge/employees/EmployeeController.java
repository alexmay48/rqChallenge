package com.example.rqchallenge.employees;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.rqchallenge.models.Employee;

@Component
public class EmployeeController implements IEmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Override
	public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
		List<Employee> employees = employeeService.getEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
		List<Employee> employeeSearch = employeeService.getEmployeeByNameSearch(searchString);
		return new ResponseEntity<List<Employee>>(employeeSearch, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> getEmployeeById(String id) {
		Employee employee = employeeService.getEmployee(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
		Integer highest = employeeService.getHighestSalaryOfEmployees();
		return new ResponseEntity<Integer>(highest, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
		List<String> topTenEarners = employeeService.getTopTenHighestEarningEmployeeNames();
		return new ResponseEntity<List<String>>(topTenEarners, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
		Employee employee = employeeService.createEmployee(employeeInput);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteEmployeeById(String id) {
		Employee employee = employeeService.deleteEmployeeById(id);
		return new ResponseEntity<String>(employee.getEmplyeeName(), HttpStatus.OK);
	}

}
