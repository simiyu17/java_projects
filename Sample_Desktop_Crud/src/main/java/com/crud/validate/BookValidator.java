/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.validate;

import com.crud.model.Book;
import java.util.Optional;

/**
 *
 * @author simiyu
 */
public class BookValidator extends ValidationSupport implements Validator<Book> {

    @Override
    public Optional<ValidationError> validate(Book k) {
        if (isNullOrEmptyString(k.getAuthor()) || isNullOrEmptyString(k.getBookName())) {
            return Optional.of(new ValidationError("Some Fields are blank !!!!!!"));
        }
     
        return Optional.empty();
    }
    
}