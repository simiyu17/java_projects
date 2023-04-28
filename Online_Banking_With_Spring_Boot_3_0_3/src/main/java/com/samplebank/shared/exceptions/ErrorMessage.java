package com.samplebank.shared.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public record ErrorMessage(HttpStatus status,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss") LocalDateTime timestamp,
                           String message, String description) {
}