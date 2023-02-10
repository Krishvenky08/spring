package com.capgemini.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.StudentDao;
import com.capgemini.model.Student;

@Service("service")
@Scope(scopeName="singleton")
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentDao dao = null;
	
	/*
	 * Transaction Types:
	 * 	1. BMT / UMT (Bean Managed Transaction)
	 *  2. CMT (Container Managed Transaction)
	 */	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean addStudent(Student student) {
		int result = dao.createStudent(student);
		return (result > 0)? true : false;
	}
	
	@Override
	public List<Student> findAllStudents() {
		return dao.readAllStudents();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean modifyStudent(Student student) {
		Student result = dao.updateStudent(student);
		return (result != null)? true : false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean removeStudent(int studentId) {
		int result = dao.deleteStudent(studentId);
		return (result == 1)?true : false;
	}
	
	@Override
	public Student findStudentById(int studentId) {
		return dao.readStudentById(studentId);
	}
}


