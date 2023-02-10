package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Student;

public interface StudentService {
	public boolean addStudent(Student student);
	public List<Student> findAllStudents();
	public boolean modifyStudent(Student student);
	public boolean removeStudent(int studentId);
	public Student findStudentById(int studentId);
}
