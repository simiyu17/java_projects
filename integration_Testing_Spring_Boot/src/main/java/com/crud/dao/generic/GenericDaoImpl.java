package com.crud.dao.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
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
@PersistenceContext(name = "FxCrudPU")
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private final Class<T> persistentClass;

    @PersistenceContext
    private EntityManager em;

    protected Session getSession() {
        Session session = em.unwrap(Session.class);
        return session;
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
    public void saveEntity(T entity) throws Exception {
       // getSession().beginTransaction();
        em.merge(entity);
        //getSession().getTransaction().commit();
    }

    /**
     * @see GenericDAO#delete(java.lang.Object)
     */
    @Override
    public void deleteEntity(T entity) throws Exception {
      //  getSession().beginTransaction();
        em.remove(entity);
        //getSession().getTransaction().commit();
    }

    /**
     * @see GenericDAO#deleteById(java.lang.Object)
     */
    @Override
    public void deleteEntityById(final ID id) throws Exception {
        T entity = this.findEntityById(id);
        if (entity != null) {
            //getSession().beginTransaction();
            em.remove(entity);
           // getSession().getTransaction().commit();
        }
    }


    /**
     * @see GenericDAO#findById(java.io.Serializable)
     */
    @Override
    public T findEntityById(final ID id) {
        //getSession().beginTransaction();

       // return getSession().get(persistentClass, id);
       return em.find(persistentClass, id);
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
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