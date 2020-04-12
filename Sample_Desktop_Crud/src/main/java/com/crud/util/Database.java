/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.util;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author simiyu
 */
public enum Database {
	MYSQL("MySQL", "jdbc:mysql://<host>:<port>/<db>?characterEncoding=UTF-8", "jdbc:mysql://<host>:<port>/<db>?characterEncoding=UTF-8", "3306", "com.mysql.jdbc.Driver", "org.hibernate.dialect.MySQL5InnoDBDialect"), 
	POSTGRES("POSTGRES", "jdbc:postgresql://<host>:<port>/<db>", "jdbc:postgresql://<host>:<port>/<db>", "5432", "org.postgresql.Driver", "org.hibernate.dialect.PostgreSQL82Dialect"), ;

	private String providerName;
	private String jdbcUrlFormat;
	private String jdbcUrlFormatToCreateDb;
	private String defaultPort;
	private String driverClass;
	private String hibernateDialect;

	private Database(String providerName, String jdbcURL, String jdbcURL2CreateDb, String defaultPort, String driverClass, String hibernateDialect) {
		this.providerName = providerName;
		this.jdbcUrlFormat = jdbcURL;
		this.jdbcUrlFormatToCreateDb = jdbcURL2CreateDb;
		this.defaultPort = defaultPort;
		this.driverClass = driverClass;
		this.hibernateDialect = hibernateDialect;
	}

	public String getConnectString(String host, String port, String databaseName) {
		String connectionURL = jdbcUrlFormat.replace("<host>", host); //$NON-NLS-1$

		if (StringUtils.isEmpty(port)) {
			port = defaultPort;
		}

		connectionURL = connectionURL.replace("<port>", port); //$NON-NLS-1$
		connectionURL = connectionURL.replace("<db>", databaseName); //$NON-NLS-1$

		return connectionURL;
	}

	public String getCreateDbConnectString(String host, String port, String databaseName) {
		String connectionURL = jdbcUrlFormatToCreateDb.replace("<host>", host); //$NON-NLS-1$

		if (StringUtils.isEmpty(port)) {
			port = defaultPort;
		}

		connectionURL = connectionURL.replace("<port>", port); //$NON-NLS-1$
		connectionURL = connectionURL.replace("<db>", databaseName); //$NON-NLS-1$

		return connectionURL;
	}

	public String getProviderName() {
		return providerName;
	}

	public String getJdbcUrlFormat() {
		return jdbcUrlFormat;
	}

	public String getDefaultPort() {
		return defaultPort;
	}
	
	@Override
	public String toString() {
		return this.providerName;
	}

	public String getHibernateConnectionDriverClass() {
		return driverClass;
	}

	public String getHibernateDialect() {
		return hibernateDialect;
	}

	/**
	 * Provider name of database. eg. MySQL
	 * 
	 * @param providerName
	 * @return
	 */
	public static Database getByProviderName(String providerName) {
		Database[] databases = values();
		for (Database database : databases) {
			if (database.providerName.equals(providerName)) {
				return database;
			}
		}

		return null;
	}
}

