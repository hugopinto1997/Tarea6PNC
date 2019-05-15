package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import com.uca.capas.domain.Student;
@Repository
public class StudentDAOImpl implements StudentDAO {
	
	@PersistenceContext(name="capas")
	 private EntityManager entityManager;
	
	@Override
	public List<Student> findALL() throws DataAccessException {
		// TODO Auto-generated method stub
		StringBuffer sb= new StringBuffer();
		sb.append("select * from public.student");
		Query query = entityManager.createNativeQuery(sb.toString(),Student.class);
		List<Student> resultset= query.getResultList();
		
		return resultset;
	}
	
	@Transactional
	public int save(Student s, Integer newRow) throws DataAccessException {
		try {
			//s.setcStudent(4);
			if(newRow==1) entityManager.persist(s);
			else entityManager.merge(s);
			entityManager.flush();
			return 1;
		}
		catch(Throwable e) {
			e.printStackTrace();
			return 1;
		}
	
	}
	
	@Transactional
	public int update(Student s) throws DataAccessException {
		try {
			 entityManager.merge(s);
			
			return 1;
		}
		catch(Throwable e) {
			e.printStackTrace();
			return 1;
		}
	
	}
	

	@Override
	public Student findOne(Integer code) throws DataAccessException {
		// TODO Auto-generated method stub
		  Student student= entityManager.find(Student.class, code);
		return student;
	}

	@Transactional
	public int delete(String name) throws DataAccessException {
		try{
			StringBuffer sb= new StringBuffer();
			sb.append("select * from public.student where s_name=:name");
			Query query = entityManager.createNativeQuery(sb.toString(),Student.class);
			query.setParameter("name", name);
			Student resultset= (Student) query.setMaxResults(1).getSingleResult();
			entityManager.remove(resultset);
			return 1;
		}catch (Throwable e) {
			e.printStackTrace();
			return 1;
		}
	}
}