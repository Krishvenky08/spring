package com.capgemini.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Student;

@Repository("dao")
public class StudentDaoImpl implements StudentDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	public StudentDaoImpl() {

	}
	
	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public int createStudent(Student student) {
		entityManager.persist(student);
		return student.getStudentId();
	}
	
	@Override
	public List<Student> readAllStudents() {
		String jpql = "Select  s From Student s";
		TypedQuery<Student> tquery = entityManager.createQuery(jpql, Student.class);
		return tquery.getResultList();
	}
	
	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public Student updateStudent(Student student) {
		student = entityManager.merge(student);
		return student;
	}

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public int deleteStudent(int studentId) {
		Student student = entityManager.find(Student.class, studentId);
		entityManager.remove(student);
		return 1;
	}

	@Override
	public Student readStudentById(int studentId) {
		return entityManager.find(Student.class, studentId);
	}
}


