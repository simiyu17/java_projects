/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud;

import com.crud.dao.common.DbUpdaterDaoImpl;
import com.crud.ui.DatabaseSetting;
import com.crud.ui.Home;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 *
 * @author simiyu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         try {
            DbUpdaterDaoImpl updateDatabase = new DbUpdaterDaoImpl();
            updateDatabase.updateDatabase();
            new Home();
        } catch (Exception e) {
            WeakReference<DatabaseSetting> ref = new WeakReference<>(new DatabaseSetting());
            ref.get().setVisible(true);
        }
    }
    
    
     public static void restart() throws IOException, InterruptedException, URISyntaxException {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";

        String classPath = System.getProperty("java.class.path"); 
        String mainClass = System.getProperty("sun.java.command"); 

        /* Build command: java -jar application.jar */
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-cp"); //$NON-NLS-1$
        command.add(classPath);
        command.add(mainClass);

        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.start();
        System.exit(0);
    }
    
}
