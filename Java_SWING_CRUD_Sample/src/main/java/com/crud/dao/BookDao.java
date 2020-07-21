/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.dao;

import com.crud.dao.common.GenericDao;
import com.crud.model.Book;
import java.util.List;

/**
 *
 * @author simiyu
 */
public interface BookDao extends GenericDao<Book, Long> {

    void save(Book book) throws Exception;

    Book findById(Long Id);

    List<Book> getBooks(String nameOrAuthor);

    void delete(Book book) throws Exception;

}
