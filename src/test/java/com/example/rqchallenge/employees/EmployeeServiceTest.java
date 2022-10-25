package com.example.rqchallenge.employees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.restapiconnection.RestApiConnection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService testingObject;

	@Mock
	private RestApiConnection restApiConnection;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getEmployeesTest() {
		Mockito.when(restApiConnection.getEmployees()).thenReturn(employeeList());
		List<Employee> list = testingObject.getEmployees();

		assert (list != null);
		assertEquals(24, list.size());
	}

	@Test
	public void getEmployeeByNameSearchTest() {
		Mockito.when(restApiConnection.getEmployees()).thenReturn(employeeList());

		List<Employee> list = testingObject.getEmployeeByNameSearch("Tiger");
		assert (list != null);
		assertEquals("Tiger Nixon", list.get(0).getEmployeeName());
		assertEquals(1, list.size());

		list = testingObject.getEmployeeByNameSearch("an");
		assert (list != null);
		assertEquals(3, list.size());

		list = testingObject.getEmployeeByNameSearch("Richard");
		assert (list != null);
		assertEquals(0, list.size());
	}

	@Test
	public void getEmployeeTest() {
		Mockito.when(restApiConnection.getEmployee("2")).thenReturn(testEmployee());
		Employee testEmployee = testingObject.getEmployee("2");
		assertEquals("Test Employee", testEmployee.getEmployeeName());

	}

	@Test
	public void getHighestSalaryOfEmployeesTest() {
		Mockito.when(restApiConnection.getEmployees()).thenReturn(employeeList());
		Integer result = testingObject.getHighestSalaryOfEmployees();
		assertEquals(725_000, result);
	}

	@Test
	public void getTopTenHighestEarningEmployeeNamesTest() {
		Mockito.when(restApiConnection.getEmployees()).thenReturn(employeeList());
		List<String> result = testingObject.getTopTenHighestEarningEmployeeNames();
		assertEquals(10, result.size());

		List<Employee> test = new ArrayList<Employee>();
		test.add(testEmployee());
		Mockito.when(restApiConnection.getEmployees()).thenReturn(test);
		result = testingObject.getTopTenHighestEarningEmployeeNames();
		assertEquals(1, result.size());

	}

	@Test
	public void createEmployeeTest() {
		Map<String, Object> employeeInput = new HashMap<String, Object>();
		Mockito.when(restApiConnection.createEmployee(Mockito.any())).thenReturn(testEmployee());

		Employee employee = testingObject.createEmployee(employeeInput);
		assertEquals("Test Employee", employee.getEmployeeName());
	}

	@Test
	public void deleteEmployeeByIdTest() {
		Mockito.when(restApiConnection.getEmployee("2")).thenReturn(testEmployee());
		Mockito.when(restApiConnection.deleteEmployee("2")).thenReturn(true);

		Employee employee = testingObject.deleteEmployeeById("2");

		assertEquals("Test Employee", employee.getEmployeeName());
	}

	public Employee testEmployee() {
		Employee test = new Employee();
		test.setId("2");
		test.setEmployeeName("Test Employee");
		test.setEmployeeSalary(12345);
		test.setEmployeeAge(20);
		test.setProfileImage("");
		return test;
	}

	public List<Employee> employeeList() {

		Type listType = new TypeToken<List<Employee>>() {
		}.getType();

		List<Employee> employeeList = new Gson().fromJson(employeeListJson(), listType);
		return employeeList;
	}

	public String employeeListJson() {
		return "[\n" + "    {\n" + "        \"id\": \"1\",\n" + "        \"employee_name\": \"Tiger Nixon\",\n"
				+ "        \"employee_salary\": 320800,\n" + "        \"employee_age\": 61,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"2\",\n"
				+ "        \"employee_name\": \"Garrett Winters\",\n" + "        \"employee_salary\": 170750,\n"
				+ "        \"employee_age\": 63,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"3\",\n" + "        \"employee_name\": \"Ashton Cox\",\n"
				+ "        \"employee_salary\": 86000,\n" + "        \"employee_age\": 66,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"4\",\n"
				+ "        \"employee_name\": \"Cedric Kelly\",\n" + "        \"employee_salary\": 433060,\n"
				+ "        \"employee_age\": 22,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"5\",\n" + "        \"employee_name\": \"Airi Satou\",\n"
				+ "        \"employee_salary\": 162700,\n" + "        \"employee_age\": 33,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"6\",\n"
				+ "        \"employee_name\": \"Brielle Williamson\",\n" + "        \"employee_salary\": 372000,\n"
				+ "        \"employee_age\": 61,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"7\",\n" + "        \"employee_name\": \"Herrod Chandler\",\n"
				+ "        \"employee_salary\": 137500,\n" + "        \"employee_age\": 59,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"8\",\n"
				+ "        \"employee_name\": \"Rhona Davidson\",\n" + "        \"employee_salary\": 327900,\n"
				+ "        \"employee_age\": 55,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"9\",\n" + "        \"employee_name\": \"Colleen Hurst\",\n"
				+ "        \"employee_salary\": 205500,\n" + "        \"employee_age\": 39,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"10\",\n"
				+ "        \"employee_name\": \"Sonya Frost\",\n" + "        \"employee_salary\": 103600,\n"
				+ "        \"employee_age\": 23,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"11\",\n" + "        \"employee_name\": \"Jena Gaines\",\n"
				+ "        \"employee_salary\": 90560,\n" + "        \"employee_age\": 30,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"12\",\n"
				+ "        \"employee_name\": \"Quinn Flynn\",\n" + "        \"employee_salary\": 342000,\n"
				+ "        \"employee_age\": 22,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"13\",\n" + "        \"employee_name\": \"Charde Marshall\",\n"
				+ "        \"employee_salary\": 470600,\n" + "        \"employee_age\": 36,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"14\",\n"
				+ "        \"employee_name\": \"Haley Kennedy\",\n" + "        \"employee_salary\": 313500,\n"
				+ "        \"employee_age\": 43,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"15\",\n" + "        \"employee_name\": \"Tatyana Fitzpatrick\",\n"
				+ "        \"employee_salary\": 385750,\n" + "        \"employee_age\": 19,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"16\",\n"
				+ "        \"employee_name\": \"Michael Silva\",\n" + "        \"employee_salary\": 198500,\n"
				+ "        \"employee_age\": 66,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"17\",\n" + "        \"employee_name\": \"Paul Byrd\",\n"
				+ "        \"employee_salary\": 725000,\n" + "        \"employee_age\": 64,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"18\",\n"
				+ "        \"employee_name\": \"Gloria Little\",\n" + "        \"employee_salary\": 237500,\n"
				+ "        \"employee_age\": 59,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"19\",\n" + "        \"employee_name\": \"Bradley Greer\",\n"
				+ "        \"employee_salary\": 132000,\n" + "        \"employee_age\": 41,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"20\",\n"
				+ "        \"employee_name\": \"Dai Rios\",\n" + "        \"employee_salary\": 217500,\n"
				+ "        \"employee_age\": 35,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"21\",\n" + "        \"employee_name\": \"Jenette Caldwell\",\n"
				+ "        \"employee_salary\": 345000,\n" + "        \"employee_age\": 30,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"22\",\n"
				+ "        \"employee_name\": \"Yuri Berry\",\n" + "        \"employee_salary\": 675000,\n"
				+ "        \"employee_age\": 40,\n" + "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n"
				+ "        \"id\": \"23\",\n" + "        \"employee_name\": \"Caesar Vance\",\n"
				+ "        \"employee_salary\": 106450,\n" + "        \"employee_age\": 21,\n"
				+ "        \"profile_image\": \"\"\n" + "    },\n" + "    {\n" + "        \"id\": \"24\",\n"
				+ "        \"employee_name\": \"Doris Wilder\",\n" + "        \"employee_salary\": 85600,\n"
				+ "        \"employee_age\": 23,\n" + "        \"profile_image\": \"\"\n" + "    }\n" + "]";
	}

}
