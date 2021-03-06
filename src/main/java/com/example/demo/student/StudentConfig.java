package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			
			Student john = new Student(
					"John Doe", 
					"john.doe@test.com", 
					LocalDate.of(2000,Month.JANUARY,5));
			Student alex = new Student(
					"Alex Doe", 
					"alex.doe@test.com", 
					LocalDate.of(2001,Month.FEBRUARY,1)
					);
			
			studentRepository.saveAll(
				List.of(john, alex));
		
		};
	}
	
}
