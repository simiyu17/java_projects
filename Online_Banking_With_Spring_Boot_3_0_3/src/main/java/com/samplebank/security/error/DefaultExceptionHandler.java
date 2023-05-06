package com.samplebank.security.error;

import com.samplebank.shared.exceptions.DataRulesViolationException;
import com.samplebank.shared.exceptions.ErrorMessage;
import com.samplebank.utilities.HelperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<ErrorMessage> handleAuthenticationException(Exception ex, WebRequest request) {
        final var message = new ErrorMessage(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return HelperUtil.buildResponseEntity(message);
    }

    @ExceptionHandler({DataRulesViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> dataIntegrityViolationException(DataRulesViolationException ex, WebRequest request){
        final var errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return HelperUtil.buildResponseEntity(errorMessage);
    }
}
