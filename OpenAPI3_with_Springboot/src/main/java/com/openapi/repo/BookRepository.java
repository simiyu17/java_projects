package com.openapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openapi.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
