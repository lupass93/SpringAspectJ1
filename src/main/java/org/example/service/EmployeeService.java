package org.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.exception.ResourceNotFoundException;
import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lupass93
 *
 */

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	
	}
	
	
	
	public Optional<Employee> getEmployeeId(Long id) throws ResourceNotFoundException {
		
		Optional<Employee> payload;
		
		boolean result = employeeRepository.existsById(id);
		
		if (result) {
			
			payload = employeeRepository.findById(id);
			
		} else {
			
			throw new ResourceNotFoundException("Resource with id "+ id + "not found");
					
		}
		
		return payload;
	
		
	}
	
	public Employee createEmployee(Employee employee) {
		
		
		return employeeRepository.save(employee);
		
		
	}
	
	public Employee updateEmployee(Long id, Employee employeeDetails) throws ResourceNotFoundException {
		

		Employee employee;
		boolean result = employeeRepository.existsById(id);
		
		if (result) {
			
			employee = new Employee(id, employeeDetails.getFirstName(), employeeDetails.getLastName(), employeeDetails.getEmailId());
			employeeRepository.save(employee);
			
		} else  {
			
			throw new ResourceNotFoundException("Resource with id"+ id +"not found!");
			
		}
		
		return employee;
		
	}
	
	public Map<String, Boolean> deleteEmployee(Long employeeId) throws ResourceNotFoundException {
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		boolean result = employeeRepository.existsById(employeeId);
		
		if (result) {
			
			Employee employee = employeeRepository.findById(employeeId).get();
			employeeRepository.delete(employee);
			response.put("deleted" , Boolean.TRUE);
			
		} else {
			
			response.put("not exists", Boolean.FALSE);
			throw new ResourceNotFoundException("Resource with id "+ employeeId + " not exits");
		}
		
		return response;
		
		
	}
	
	
	
	

}
