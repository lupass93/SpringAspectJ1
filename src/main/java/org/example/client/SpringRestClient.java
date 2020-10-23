package org.example.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.example.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public final class SpringRestClient {
	
	private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8080/webApp/employees";
	private static final String GET_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/webApp/getEmployee";
	private static final String POST_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/webApp/newEmployee";
	private static final String PUT_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/webApp/updateEmployee";
	private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/webApp/deleteEmployee";
	
	@Autowired
    private static RestTemplate restTemplate;
    

    public static void main(String[] args) {
        SpringRestClient springRestClient = new SpringRestClient();

        // Step1: first create a new employee
        springRestClient.createEmployee();

        // Step 2: get new created employee from step1
        springRestClient.getEmployeeById();

        // Step3: get all employees
        springRestClient.getEmployees();

        // Step4: Update employee with id = 1
        springRestClient.updateEmployee();

        // Step5: Delete employee with id = 1
        springRestClient.deleteEmployee();
    }
    
    private void getEmployees() {
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    	ResponseEntity<String> result = restTemplate.exchange(GET_EMPLOYEES_ENDPOINT_URL, HttpMethod.GET, entity,
                String.class);
                
                
      	System.out.println(result);
    }
    
    
    private void getEmployeeById() {
    		
    	Map<String, String> params = new HashMap<String, String> ();
    	params.put("id", "1");
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	Employee result = restTemplate.getForObject(GET_EMPLOYEE_ENDPOINT_URL, Employee.class, params);
    	
    	System.out.println(result);
    	
    }
    
    private void createEmployee() {

        Employee newEmployee = new Employee("admin", "admin", "admin@gmail.com");

        RestTemplate restTemplate = new RestTemplate();
        Employee result = restTemplate.postForObject(POST_EMPLOYEE_ENDPOINT_URL, newEmployee, Employee.class);

        System.out.println(result);
    }

    private void updateEmployee() {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "1");
        Employee updatedEmployee = new Employee("admin123", "admin123", "admin123@gmail.com");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(PUT_EMPLOYEE_ENDPOINT_URL, updatedEmployee, params);
    }

    private void deleteEmployee() {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "1");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, params);
    }
    
    
    
}
