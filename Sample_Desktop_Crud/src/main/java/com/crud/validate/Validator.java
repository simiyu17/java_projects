/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.validate;

import java.util.Optional;

/**
 *
 * @author simiyu
 */
public interface Validator <K> {
     Optional<ValidationError> validate(K k);
}
