package com.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("No Order Found !!!");
    }

    public OrderNotFoundException(Long orderId) {
        super("No Order Found With Id  "+orderId);
    }
}
