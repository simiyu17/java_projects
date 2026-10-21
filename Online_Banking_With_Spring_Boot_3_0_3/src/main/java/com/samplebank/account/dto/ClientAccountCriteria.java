package com.samplebank.account.dto;

import com.samplebank.account.domain.ClientAccount;

import java.io.Serializable;

public record ClientAccountCriteria(
        Long clientId,
        ClientAccount.AccountStatus accountStatus
) implements Serializable {}
