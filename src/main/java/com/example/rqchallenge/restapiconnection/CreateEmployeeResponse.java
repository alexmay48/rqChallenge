package com.example.rqchallenge.restapiconnection;

import com.example.rqchallenge.models.Employee;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * The response to creating an employee doesn't return integer values for age
 * and salary. It also doesn't have the same named fields as the typical
 * Employee response from the dummy API.
 *
 */
@Data
public class CreateEmployeeResponse {

	@SerializedName("id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("age")
	private String age;

	@SerializedName("salary")
	private String salary;

	public Employee mapToRqEmployee() {
		Employee employee = new Employee();
		employee.setId(id);
		employee.setEmployeeAge(Integer.parseInt(age));
		employee.setEmployeeSalary(Integer.parseInt(salary));
		employee.setEmplyeeName(this.name);
		employee.setProfileImage("");
		return employee;
	}

}
