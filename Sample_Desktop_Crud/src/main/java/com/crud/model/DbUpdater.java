/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author simiyu
 */
@Entity
public class DbUpdater extends BaseEntity {
    
    @Column(name = "name")
    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
