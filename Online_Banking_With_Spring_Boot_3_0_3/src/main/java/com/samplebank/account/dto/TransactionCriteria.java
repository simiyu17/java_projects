package com.samplebank.account.dto;

import com.samplebank.account.domain.AccountTransaction;

import java.io.Serializable;
import java.time.LocalDateTime;

public record TransactionCriteria(
        Long clientAccountId,
        AccountTransaction.TransactionType transactionType,
        LocalDateTime fromDate,
        LocalDateTime toDate
) implements Serializable {
}
