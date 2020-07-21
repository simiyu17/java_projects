/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.util;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author simiyu
 */
public class JpaUtil {

    private static EntityManagerFactory emf = null;

    public JpaUtil() {
    }

    /**
     * Create entity manager factory.
     *
     * @return entity manager factory from persistence unit
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        try {
            if (emf == null) {
                Map<Object, Object> properties = new HashMap<Object,Object>();
                properties.put("connection.pool_size", 1);
                properties.put("cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");
                properties.put("hibernate.hbm2ddl.auto", "update");
                properties.put("hibernate.show_sql", false);
                emf = Persistence.createEntityManagerFactory("primaryPU", properties);
            }
            return emf;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Close the entity manager factory.
     */
    public static void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }

    /**
     * Gets new entity manager instance.
     *
     * @return new entity manager instance
     */
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

}
