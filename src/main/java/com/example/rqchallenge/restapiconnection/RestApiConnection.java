package com.example.rqchallenge.restapiconnection;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.models.RestApiBaseResponse;
import com.example.rqchallenge.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RestApiConnection {

	public List<Employee> getEmployees() {
		try {
			// create a client
			HttpClient client = HttpClient.newHttpClient();

			// create a request
			HttpRequest request = HttpRequest
					.newBuilder(URI.create("https://dummy.restapiexample.com/api/v1/employees"))
					.header("accept", "application/json").build();

			// use the client to send the request

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			Type listType = new TypeToken<RestApiBaseResponse<List<Employee>>>() {
			}.getType();

			RestApiBaseResponse<List<Employee>> baseResponse = new Gson().fromJson(response.body(), listType);

			List<Employee> employees = baseResponse.getData();
			return employees;

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Employee getEmployee(String id) {
		try {
			// create a client
			HttpClient client = HttpClient.newHttpClient();

			// create a request
			HttpRequest request = HttpRequest
					.newBuilder(URI.create("https://dummy.restapiexample.com/api/v1/employee/" + id))
					.header("accept", "application/json").build();

			// use the client to send the request

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			Type listType = new TypeToken<RestApiBaseResponse<Employee>>() {
			}.getType();

			RestApiBaseResponse<Employee> baseResponse = new Gson().fromJson(response.body(), listType);

			Employee employee = baseResponse.getData();

			return employee;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Employee createEmployee(Map<String, Object> employeeInput) {
		try {
			// create a client
			HttpClient client = HttpClient.newHttpClient();

			String parameters = Utils.convertToUriParameters(employeeInput);

			// create a request
			HttpRequest request = HttpRequest
					.newBuilder(URI.create("https://dummy.restapiexample.com/api/v1/create?" + parameters))
					.POST(HttpRequest.BodyPublishers.noBody()).header("accept", "application/json").build();

			// use the client to send the request

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			Type listType = new TypeToken<RestApiBaseResponse<CreateEmployeeResonse>>() {
			}.getType();

			RestApiBaseResponse<CreateEmployeeResonse> baseResponse = new Gson().fromJson(response.body(), listType);

			Employee employee = baseResponse.getData().mapToRqEmployee();

			return employee;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteEmployee(String id) {

		try {
			// create a client
			HttpClient client = HttpClient.newHttpClient();

			// create a request
			HttpRequest request = HttpRequest
					.newBuilder(URI.create("https://dummy.restapiexample.com/api/v1/delete/" + id)).DELETE()
					.header("accept", "application/json").build();

			// use the client to send the request

			client.send(request, HttpResponse.BodyHandlers.ofString());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return false;
	}

}
