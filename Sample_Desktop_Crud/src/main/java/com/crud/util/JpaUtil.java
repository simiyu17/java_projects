/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package com.crud.util;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA Util
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
            Map properties = new HashMap();
            properties.put("hibernate.dialect", AppConfig.getDatabaseProviderName().equals("MySQL") ? "org.hibernate.dialect.MySQL5InnoDBDialect" : "org.hibernate.dialect.PostgreSQL82Dialect");
            properties.put("hibernate.connection.driver_class", AppConfig.getDefaultDatabase().getHibernateConnectionDriverClass());
            properties.put("hibernate.connection.url", AppConfig.getConnectString());
            properties.put("hibernate.connection.username", AppConfig.getDatabaseUser());
            properties.put("hibernate.connection.password", AppConfig.getDatabasePassword());
            //JDBC connection pool (use the built-in)
            properties.put("connection.pool_size", 1);
            //Disable the second-level cache
            properties.put("cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");
            properties.put("hibernate.hbm2ddl.auto", "update");
            properties.put("hibernate.show_sql", false);
            emf = Persistence.createEntityManagerFactory("crudPU", properties);
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
