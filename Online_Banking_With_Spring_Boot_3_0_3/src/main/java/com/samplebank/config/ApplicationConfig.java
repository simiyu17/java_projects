package com.samplebank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

    @Value("${online.banking.client.account.length:14}")
    private Long clientAccountLength;

    public Long getClientAccountLength() {
        return clientAccountLength;
    }
}
