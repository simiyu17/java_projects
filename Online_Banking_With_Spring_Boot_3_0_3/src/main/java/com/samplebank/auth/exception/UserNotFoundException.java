
package com.samplebank.auth.exception;

/**
 *
 * @author simiyu
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
    
}
