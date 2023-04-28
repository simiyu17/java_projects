package com.samplebank.shared.exceptions;

import com.samplebank.auth.exception.UserNotFoundException;
import com.samplebank.utilities.HelperUtil;
import io.jsonwebtoken.JwtException;
import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { UserNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(RuntimeException ex, WebRequest request) {
        final var message = new ErrorMessage(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return HelperUtil.buildResponseEntity(message);
    }

    @ExceptionHandler(value = { BadCredentialsException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMessage> unAuthenticatedException(BadCredentialsException ex, WebRequest request) {
        final var message = new ErrorMessage(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return HelperUtil.buildResponseEntity(message);
    }

    @ExceptionHandler(value = { JwtException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMessage> unAuthorizedException(JwtException ex, WebRequest request) {
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
