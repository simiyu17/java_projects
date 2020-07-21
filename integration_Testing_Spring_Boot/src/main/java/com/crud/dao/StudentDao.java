package com.crud.dao;

import com.crud.model.Student;
import java.util.List;

public interface StudentDao {
    
    void save(Student std) throws Exception;
	
	Student findById(Long id);
	
	List<Student> getStudents();
	
	void delete(Student std) throws Exception;
}