package org.example.application;

import org.example.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@SpringBootApplication
public class Application {
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		Employee employee = new Employee("Luca", "Passaretta", "lupass93@gmail.com");
		Gson gson = new Gson();
		String employyeJson = gson.toJson(employee);
		System.out.println(employyeJson);
		
		
		
		SpringApplication.run(Application.class, args);
		
	}
	
	

}
