package com.sample.dao.student;

import com.sample.model.Student;
import java.util.List;

public interface StudentDao {
    
    void save(Student std) throws Exception;
	
	Student findById(Long id);
	
	List<Student> getStudents() throws Exception;
	
	void delete(Student std) throws Exception;
}