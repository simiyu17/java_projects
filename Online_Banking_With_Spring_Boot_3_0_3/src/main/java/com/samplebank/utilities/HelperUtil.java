package com.samplebank.utilities;

import com.samplebank.shared.exceptions.ErrorMessage;
import org.springframework.http.ResponseEntity;

public final class HelperUtil {

    private HelperUtil(){}

    public static ResponseEntity<ErrorMessage> buildResponseEntity(ErrorMessage message){
        return ResponseEntity.status(message.status()).body(message);
    }
}
