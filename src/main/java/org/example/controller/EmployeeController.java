package org.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.example.exception.ResourceNotFoundException;
import org.example.model.Employee;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webApp/")
public final class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		
		return employeeService.getAllEmployees();
		
	}
	
	@GetMapping("/getEmployee")
	public ResponseEntity<Employee> getEmployeeById(@RequestParam(value = "id") Long id) {
		 
		Optional<Employee> employeeOp = null;

		
		try {
			
			employeeOp = employeeService.getEmployeeId(id);
		
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(employeeOp.get());
		
		
	}
	
	@PostMapping("/newEmployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		
		Employee payload = employeeService.createEmployee(employee);
		
		return ResponseEntity.ok(payload);
		
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@RequestParam(value = "id") Long id, @Valid @RequestBody Employee employeeDetails) {
		
		Employee updatedEmployee = null;
		try {
			updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(updatedEmployee);
		
		
	}
	
	@DeleteMapping("/deleteEmployee")
	public Map<String, Boolean> deleteEmployee(@RequestParam(value="id") Long id) {
		
		
		Map<String, Boolean> response = null;
		
		
		try {
			response = employeeService.deleteEmployee(id);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
		
		
	}
	
	
	
	

}
