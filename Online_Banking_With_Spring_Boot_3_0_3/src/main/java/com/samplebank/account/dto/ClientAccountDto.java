package com.samplebank.account.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public record ClientAccountDto (
    Long id,

    @NotNull
    String accountType,

    String accountStatus,

    String accountNumber,

    BigDecimal balance
)implements Serializable {}
