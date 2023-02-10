package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dto.StudentDTO;
import com.capgemini.model.Student;
import com.capgemini.service.StudentService;

@RestController
@RequestMapping(path = "students")
@CrossOrigin
public class StudentController {
	@Autowired
	private StudentService service;

	// http://localhost:9091/student-api/students/
	@GetMapping(path = "/")
	public ResponseEntity<List<Student>> getAllStudents() {
		ResponseEntity<List<Student>> response = null;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Student> students = service.findAllStudents();
		response = new ResponseEntity<List<Student>>(students, HttpStatus.OK);
		return response;
	}

	// http://localhost:9091/student-api/students/10
	@GetMapping(path = "{studentId}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable("studentId") int studentId) {
		ResponseEntity<StudentDTO> response = null;
		Student student = service.findStudentById(studentId);
		if (student != null) {
			StudentDTO dto = new StudentDTO(student.getStudentId(), student.getStudentName(), student.getScore(),
					student.getAddress().getCity(), student.getAddress().getState(), student.getAddress().getPin());
			response = new ResponseEntity<StudentDTO>(dto, HttpStatus.OK);
		} else {
			response = new ResponseEntity<StudentDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	// http://localhost:9091/student-api/students
	@PostMapping(path = "/")
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		ResponseEntity<String> response = null;
		boolean result = service.addStudent(student);
		if (result)
			response = new ResponseEntity<String>("Student data is added in database", HttpStatus.CREATED);
		else
			response = new ResponseEntity<String>("Problem saving student data in database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}

	// http://localhost:9091/student-api/students/160
	@DeleteMapping(path = "{studentId}")
	public void deleteStudentByStudentId(@PathVariable("studentId") int studentId) {
		service.removeStudent(studentId);
	}

	// http://localhost:9091/student-api/students/
	@PutMapping(path = "/")
	public Student updateStudent(@RequestBody Student student) {
		boolean result = service.modifyStudent(student);
		if (result)
			student = service.findStudentById(student.getStudentId());
		return student;
	}

//	// http://localhost:9091/student-api/students/login.do
//	@PostMapping(path = "login.do")
//	public ResponseEntity<?> authenticateUserAndGenerateToken(@RequestBody Request request)throws Exception 
//	{
//		try {
//			System.out.println("Inside authenticateUserAndGenerateToken method before calling authenticate method");
//			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//			System.out.println("Inside authenticateUserAndGenerateToken method after calling authenticate method");
//			final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//
//			// if credential are correct will perform following step or else BadCredentialException will be raised by authenticate method
//			final String jwt = jwtTokenUtil.generateToken(userDetails);
//			return ResponseEntity.ok(new Response(jwt));
//
//		} catch (BadCredentialsException e) {
//			throw new Exception("Incorrect username or password", e);
//		}
//	}
}