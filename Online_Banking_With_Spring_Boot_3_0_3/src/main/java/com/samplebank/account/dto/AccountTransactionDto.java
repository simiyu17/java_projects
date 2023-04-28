package com.samplebank.account.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record AccountTransactionDto (
    Long id,

    String transactionType,

    String description,

    BigDecimal amount
) implements Serializable {}
