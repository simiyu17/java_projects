/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.dao.common;

import com.crud.model.DbUpdater;



/**
 *
 * @author simiyu
 */
public class DbUpdaterDaoImpl extends GenericDaoImpl<DbUpdater, Long> implements DbUpdaterDao{

    @Override
    public Integer updateDatabase() {
        return countAll();
    }
    
}
