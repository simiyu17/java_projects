package com.samplebank.account.service;

import com.samplebank.account.dto.ClientAccountDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ClientAccountService {

    void createClientAccount(@NotNull Long clientId, @NotNull ClientAccountDto clientAccountDto);

    ClientAccountDto getAccountById(Long accountId);

    List<ClientAccountDto> getClientAccounts(Long clientId, String status);
}
