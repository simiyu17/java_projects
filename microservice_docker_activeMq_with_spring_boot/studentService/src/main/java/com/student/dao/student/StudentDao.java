package com.student.dao.student;

import java.util.List;
import com.student.model.Student;

public interface StudentDao {
    
    void save(Student std) throws Exception;
	
	Student findById(Long id);
	
	List<Student> getStudents();
	
	void delete(Student std) throws Exception;
}