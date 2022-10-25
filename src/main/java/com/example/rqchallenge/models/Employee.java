package com.example.rqchallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Employee {

	private String id;

	@JsonProperty("employee_name")
	@SerializedName("employee_name")
	private String employeeName;

	@JsonProperty("employee_salary")
	@SerializedName("employee_salary")
	private Integer employeeSalary;

	@JsonProperty("employee_age")
	@SerializedName("employee_age")
	private Integer employeeAge;

	@JsonProperty("profile_image")
	@SerializedName("profile_image")
	private String profileImage;

}
