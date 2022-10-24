package com.example.rqchallenge.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Employee {

	@SerializedName("id")
	private String id;

	@SerializedName("employee_name")
	private String emplyeeName;

	@SerializedName("employee_salary")
	private Integer employeeSalary;

	@SerializedName("employee_age")
	private Integer employeeAge;

	@SerializedName("profile_image")
	private String profileImage;

}
