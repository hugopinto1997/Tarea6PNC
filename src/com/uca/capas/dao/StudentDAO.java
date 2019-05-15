package com.uca.capas.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Student;

public interface StudentDAO {
	public int save(Student s,Integer newRow) throws DataAccessException;
	public int update(Student s) throws DataAccessException;
	public int delete(String name) throws DataAccessException;
	public List<Student> findALL() throws DataAccessException;
	public Student findOne(Integer code) throws DataAccessException;

}