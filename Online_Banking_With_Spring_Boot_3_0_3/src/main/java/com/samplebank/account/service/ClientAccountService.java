package com.samplebank.account.service;

import com.samplebank.account.dto.ClientAccountDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientAccountService {

    void createClientAccount(@NotNull Long clientId);

    ClientAccountDto getAccountById(Long accountId);

    List<ClientAccountDto> getClientAccounts(Long clientId, String status);

    List<ClientAccountDto> getMyClientAccounts(@NotNull Authentication authentication, String status);
}
