package com.book.dao.common;

import java.io.Serializable;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.criterion.Criterion;

/**
 * Generic Repository, providing basic CRUD operations
 *
 * @author Simiyu
 *
 * @param <T> the entity type
 * @param <ID> the primary key type
 */
public interface GenericDAO<T, ID extends Serializable> {

    /**
     * Get the Class of the entity.
     *
     * @return the class
     */
    Class<T> getEntityClass();

    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity
     */
    T findEntityById(final ID id);

    /**
     * save an entity. This can be either a INSERT or UPDATE in the database.
     *
     * @param entity the entity to save
     *
     * @return the saved entity
     */    
     T saveEntity(final T entity) throws Exception;


    /**
     * delete an entity from the database.
     *
     * @param entity the entity to delete
     */
    void deleteEntity(final T entity) throws Exception;

    /**
     * delete an entity by its primary key
     *
     * @param id the primary key of the entity to delete
     */
    void deleteEntityById(final ID id) throws Exception;


    public List<T> findByCriteria(final int firstResult,
            final int maxResults, final CriteriaQuery<T> select);


}
