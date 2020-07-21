package com.crudsample.dao.student;

import com.crudsample.model.Student;
import java.util.List;

public interface StudentDao {
    
    void save(Student std) throws Exception;
	
	Student findById(Long id);
	
	List<Student> getStudents() throws Exception;
	
	void delete(Student std) throws Exception;
}