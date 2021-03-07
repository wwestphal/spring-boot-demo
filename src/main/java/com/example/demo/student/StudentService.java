package com.example.demo.student;

	import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = this.studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("student does not exits - cannot delete");
		}
		this.studentRepository.deleteById(studentId);
	}
	
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Optional<Student> studentFound = this.studentRepository.findById(studentId);
		if(studentFound.isEmpty()) {
			throw new IllegalStateException("student does not exits - cannot update");
		}
		Student student = studentFound.get();
		if(!name.isEmpty()) {
			student.setName(name);
		}
		if(!email.isEmpty()) {
			student.setEmail(email);
		}
	}
	
}
