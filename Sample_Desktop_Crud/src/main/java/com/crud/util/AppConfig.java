/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.util;

import java.io.File;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 *
 * @author simiyu
 */
public class AppConfig {

    public static final String DATABASE_URL = "database_url";
    public static final String DATABASE_PORT = "database_port";
    public static final String DATABASE_NAME = "database_name";
    public static final String DATABASE_USER = "database_user";
    public static final String DATABASE_PASSWORD = "database_pass";
    public static final String CONNECTION_STRING = "connection_string";
    public static final String DATABASE_PROVIDER_NAME = "database_provider_name";

    private static PropertiesConfiguration config;

    static {
        try {
            File configFile = new File("crud.config.properties");

            if (!configFile.exists()) {
                configFile.createNewFile();
            }

            config = new PropertiesConfiguration(configFile);
            config.setAutoSave(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PropertiesConfiguration getConfig() {
        return config;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return config.getBoolean(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return config.getInt(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        config.setProperty(key, value);
    }

    public static String getString(String key) {
        return config.getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        return config.getString(key, defaultValue);
    }

    public static void put(String key, boolean value) {
        config.setProperty(key, value);
    }

    public static void put(String key, String value) {
        config.setProperty(key, value);
    }

    public static String getDatabaseHost() {
        return config.getString(DATABASE_URL, "localhost");
    }

    public static void setDatabaseHost(String url) {
        config.setProperty(DATABASE_URL, url);
    }

    public static String getConnectString() {
        return config.getString(CONNECTION_STRING, Database.MYSQL.getConnectString("", "", ""));
    }

    public static void setConnectString(String connectionString) {
        config.setProperty(CONNECTION_STRING, connectionString);
    }

    public static String getDatabasePort() {
        return config.getString(DATABASE_PORT, null);
    }

    public static void setDatabasePort(String port) {
        config.setProperty(DATABASE_PORT, port);
    }

    public static String getDatabaseName() {
        return config.getString(DATABASE_NAME, "housedb");
    }

    public static void setDatabaseName(String name) {
        config.setProperty(DATABASE_NAME, name);
    }

    public static String getDatabaseUser() {
        return config.getString(DATABASE_USER, "houseuser");
    }

    public static void setDatabaseUser(String user) {
        config.setProperty(DATABASE_USER, user);
    }

    public static String getDatabasePassword() {
        return config.getString(DATABASE_PASSWORD, "housepassword");
    }

    public static void setDatabasePassword(String password) {
        config.setProperty(DATABASE_PASSWORD, password);
    }

    public static void setDatabaseProviderName(String databaseProviderName) {
        config.setProperty(DATABASE_PROVIDER_NAME, databaseProviderName);
    }

    public static String getDatabaseProviderName() {
        return config.getString(DATABASE_PROVIDER_NAME, Database.MYSQL.getProviderName());
    }

    public static Database getDefaultDatabase() {
        return Database.getByProviderName(getDatabaseProviderName());
    }

}
