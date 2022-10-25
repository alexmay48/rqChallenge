package com.example.rqchallenge.restapiconnection;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.rqchallenge.exceptions.ApiException;
import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The connection layer connects to the 3rd party API requests.
 * 
 * @author alexmay
 *
 */
@Component
public class RestApiConnection {

	static final Logger LOG = LogManager.getLogger(RestApiConnection.class);

	public List<Employee> getEmployees() {
		try {
			// create a client
			HttpClient client = HttpClient.newHttpClient();

			// create a request
			HttpRequest request = HttpRequest
					.newBuilder(URI.create("https://dummy.restapiexample.com/api/v1/employees"))
					.header("accept", "application/json").build();

			// use the client to send the request

			LOG.info("Request: {}", request);

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				LOG.info("Response ERROR: {}", response);
				throw new ApiException(HttpStatus.valueOf(response.statusCode()), response.body());
			}

			LOG.info("Response: {}", response.body());

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

			LOG.info("Request: {}", request);

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				LOG.info("Response ERROR: {}", response);
				throw new ApiException(HttpStatus.valueOf(response.statusCode()), response.body());
			}

			LOG.info("Response: {}", response.body());

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

			LOG.info("Request: {}", request);

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				LOG.info("Response ERROR: {}", response);
				throw new ApiException(HttpStatus.valueOf(response.statusCode()), response.body());
			}

			LOG.info("Response: {} : {}", response.uri(), response.body());

			Type listType = new TypeToken<RestApiBaseResponse<CreateEmployeeResponse>>() {
			}.getType();

			RestApiBaseResponse<CreateEmployeeResponse> baseResponse = new Gson().fromJson(response.body(), listType);

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

			LOG.info("Request: {}", request);

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			LOG.info("Response: {}", response.body());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return false;
	}

}
