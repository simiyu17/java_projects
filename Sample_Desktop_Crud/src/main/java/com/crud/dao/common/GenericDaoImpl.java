package com.crud.dao.common;

import com.crud.util.JpaUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JPA implementation of the GenericRepository.
 *
 * @author Simiyu
 *
 * @param <T> The persistent type
 * @param <ID> The primary key type
 */
public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    private final Class<T> persistentClass;

    private Logger log = LoggerFactory.getLogger(getClass());

    private EntityManager entityManager;

    protected SessionFactory sessionFactory;

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = JpaUtil.getEntityManager();
        }

        return entityManager;
    }

    protected Session getSession() {
        Session session = getEntityManager().unwrap(Session.class);
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
        getSession().beginTransaction();
        getSession().merge(entity);
        getSession().getTransaction().commit();
    }

    /**
     * @see GenericDAO#delete(java.lang.Object)
     */
    @Override
    public void deleteEntity(T entity) throws Exception {
        if (getSession().getTransaction() == null) {
            getSession().beginTransaction();
        }
        getSession().delete(entity);
        getSession().getTransaction().commit();
    }

    /**
     * @see GenericDAO#deleteById(java.lang.Object)
     */
    @Override
    public void deleteEntityById(final ID id) throws Exception {
        T entity = this.findEntityById(id);
        if (entity != null) {
            if (getSession().getTransaction() == null) {
                getSession().beginTransaction();
            }
            getSession().delete(entity);
            getSession().getTransaction().commit();
        }
    }


    /**
     * @see GenericDAO#findById(java.io.Serializable)
     */
    @Override
    public T findEntityById(final ID id) {
        getSession().beginTransaction();

        return getSession().get(persistentClass, id);
    }
    
    
    @Override
     public int countAll() {
        return countByCriteria();
    }
     
    @Override
     public int countByCriteria(Criterion... criterion) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        return ((Long) crit.list().get(0)).intValue();
    }



    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(int firstResult, final int maxResults, CriteriaQuery<T> select) {
        TypedQuery<T> typedQuery = getEntityManager().createQuery(select);
        
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
