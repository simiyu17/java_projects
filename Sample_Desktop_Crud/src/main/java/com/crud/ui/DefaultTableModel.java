/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.ui;

import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Locale;

public abstract class DefaultTableModel<T> extends AbstractTableModel {
    
    protected static DecimalFormat formatter = new DecimalFormat("#,##0.00"); 

    protected List<T> entities = Lists.newArrayList();

    public abstract String[] getColumnLabels();

    @Override
    public int getRowCount() {
        return entities.size();
    }

    @Override
    public int getColumnCount() {
        return getColumnLabels().length;
    }

    @Override
    public String getColumnName(int column) {
        return getColumnLabels()[column];
    }

    public void addEntity(T entity) {
        entities.add(entity);
        fireTableDataChanged();
    }

    public void addEntities(List<T> entities) {
        this.entities.addAll(entities);
        fireTableDataChanged();
    }

    public T getEntityByRow(int rowIndex) {
        return entities.get(rowIndex);
    }

    public void removeRow(int row) {
        entities.remove(row);
        fireTableDataChanged();
    }
    
     public void remove(T entity) {
        entities.remove(entity);
        fireTableDataChanged();
    }

    public void clear() {
        entities.clear();
    }
    
     public BigDecimal negate(BigDecimal value) {
        BigDecimal factor = new BigDecimal(-1);
        return value.multiply(factor);
    }
     
       protected String formatDate(Date date){
        return new SimpleDateFormat("yyyy-MM-dd",   Locale.getDefault()).format(date);
    }
}
