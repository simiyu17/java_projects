/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.dao;

import com.crud.dao.common.GenericDaoImpl;
import com.crud.model.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author simiyu
 */
public class BookDaoImpl extends GenericDaoImpl<Book, Long> implements BookDao{
    
     @Override
    public void save(Book book) throws Exception {
        saveEntity(book);
    }

    @Override
    public Book findById(Long Id) {
        return findEntityById(Id);
    }

    @Override
    public List<Book> getBooks(String nameOrAuthor) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        
        if (nameOrAuthor != null) {
            Predicate sname = builder.like(root.get("bookName"), "%"+nameOrAuthor+"%");
            Predicate fname = builder.like(root.get("author"), "%"+nameOrAuthor+"%");
            predicates.add(builder.or(sname, fname));
        }
        
        if (predicates.size() > 0) {
            criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        }
        return findByCriteria(-1, -1, criteria.select(root));
   
    }

    @Override
    public void delete(Book book) throws Exception {
        deleteEntityById(book.getId());
    }
    
}