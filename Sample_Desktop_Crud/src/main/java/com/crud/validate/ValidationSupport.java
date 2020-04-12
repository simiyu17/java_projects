/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.validate;

import com.google.common.base.Strings;

/**
 *
 * @author simiyu
 */
public class ValidationSupport {
    
     boolean isNullOrEmptyString(String value) {
        return Strings.isNullOrEmpty(value);
    }

    boolean isNullValue(Object value) {
        return value == null;
    }

    boolean isValueGreaterThanZero(long value) {
        return value > 0;
    }

    boolean isValueGreaterThanZero(double value) {
        return value > 0;
    }
}
