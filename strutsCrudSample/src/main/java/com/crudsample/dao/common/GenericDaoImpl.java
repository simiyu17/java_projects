package com.crudsample.dao.common;

import com.crudsample.util.JpaUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private final Class<T> persistentClass;

    protected Logger log = LoggerFactory.getLogger(getClass());

    protected EntityManager entityManager;
    
    public void setEm(EntityManager em) {
        this.entityManager = em;
    }

    protected EntityManager getEntityManager() {
        if (this.entityManager == null) {
            this.entityManager = JpaUtil.getEntityManager();
        }
        return this.entityManager;
    }

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityManager = getEntityManager();
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
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }

    /**
     * @see GenericDAO#delete(java.lang.Object)
     */
    @Override
    public void deleteEntity(T entity) throws Exception {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }

    /**
     * @see GenericDAO#deleteById(java.lang.Object)
     */
    @Override
    public void deleteEntityById(final ID id) throws Exception {
        T entity = this.findEntityById(id);
        if (entity != null) {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(entity);
            this.entityManager.getTransaction().commit();
        }
    }

    /**
     * @see GenericDAO#findById(java.io.Serializable)
     */
    @Override
    public T findEntityById(final ID id) {
        return entityManager.find(persistentClass, id);
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @Override
    public List<T> findByCriteria(int firstResult, final int maxResults, CriteriaQuery<T> select) {
        TypedQuery<T> typedQuery = this.entityManager.createQuery(select);

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
