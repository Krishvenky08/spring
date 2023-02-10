package com.capgemini.dao;

import java.util.List;

import com.capgemini.model.Student;

public interface StudentDao {
	public int createStudent(Student student);
	public List<Student> readAllStudents();
	public Student updateStudent(Student student);
	public int deleteStudent(int studentId);
	public Student readStudentById(int studentId);
}
