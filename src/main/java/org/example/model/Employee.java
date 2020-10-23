package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "employees")
public final class Employee {
	
	@NotNull
	private long id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@Email
	@NotNull
	private String emailId;
	
	public Employee() {
		
		super();
		
	}
	
	public Employee(String firstName, String lastName, String emailId) {
		
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		
	}
	
	public Employee(Long id, String firstName, String lastName, String emailId) {
		
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.id = id;
	
	}
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		
		return this.id;
		
	}
	
	
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		
		return this.firstName;
	}
	
	
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		
		return this.lastName;
		
		
	}
	
	@Column(name = "email_address", nullable = false)
	public String getEmailId() {
		
		return this.emailId;
	}
	
	@Override
	public String toString() {
		
		return "Employee [id=" + id +", firstname=" + firstName + ", lastname=" + lastName + ", emailId=" + emailId +"]"; 
	
	}

}
