package com.samplebank.shared.exceptions;

public class DataRulesViolationException extends RuntimeException {

    public DataRulesViolationException(String message){
        super(message);
    }
}
