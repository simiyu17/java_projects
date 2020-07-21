package com.book.dao.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

/**
 * JPA implementation of the GenericRepository.
 *
 * @author Simiyu
 *
 * @param <T> The persistent type
 * @param <ID> The primary key type
 */

public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private final Class<T> persistentClass;

    @PersistenceContext
    protected EntityManager em;
    

    protected Session getSession() {
        Session session = em.unwrap(Session.class);
        return session;
    }
    
    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("primaryPU");;
        return emf.createEntityManager();
    }
    

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

    }

    public GenericDaoImpl(final Class<T> persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }

    /**
     * @see GenericDAO#getEntityClass()
     */
    @Override
    public Class<T> getEntityClass() {
        return persistentClass;
    }

    /**
     * @see GenericDAO#save(Object) #save(java.lang.Object)
     */
    
     @Override
    public T saveEntity(T entity) throws Exception {
        return em.merge(entity);
    }

    /**
     * @see GenericDAO#delete(java.lang.Object)
     */
    @Override
    public void deleteEntity(T entity) throws Exception {
        em.remove(entity);
    }

    /**
     * @see GenericDAO#deleteById(java.lang.Object)
     */
    @Override
    public void deleteEntityById(final ID id) throws Exception {
        T entity = this.findEntityById(id);
        if (entity != null) {
            em.remove(entity);
        }
    }


    /**
     * @see GenericDAO#findById(java.io.Serializable)
     */
    @Override
    public T findEntityById(final ID id) {
       return em.find(persistentClass, id);
    }




    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(int firstResult, final int maxResults, CriteriaQuery<T> select) {
        TypedQuery<T> typedQuery = em.createQuery(select);
        
        List<T> thelist = typedQuery.getResultList();

        if (firstResult > 0 || maxResults > 0) {
            while (firstResult < thelist.size()) {
                typedQuery.setFirstResult(firstResult - 1);
                typedQuery.setMaxResults(maxResults);
                firstResult += maxResults;
            }
        }

        return typedQuery.getResultList();

    }



}
