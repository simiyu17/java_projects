/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.util;

import com.crud.dao.BookDaoImpl;
import com.crud.validate.BookValidator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author simiyu
 */
public class CommonFunction {

    protected Logger log = LoggerFactory.getLogger(getClass());
    
    protected BookDaoImpl bookDao = new BookDaoImpl();
    protected BookValidator bookValidator = new BookValidator();
    

    protected String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date);
    }
}
