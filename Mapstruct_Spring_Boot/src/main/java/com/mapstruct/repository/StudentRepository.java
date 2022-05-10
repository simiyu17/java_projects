package com.mapstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapstruct.entiy.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
