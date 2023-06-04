package com.samplebank.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class HelperUtil {

    private HelperUtil(){}

    public static <T> ResponseEntity<T> buildResponseEntity(T body, HttpStatus status){
        return ResponseEntity.status(status).body(body);
    }
}
