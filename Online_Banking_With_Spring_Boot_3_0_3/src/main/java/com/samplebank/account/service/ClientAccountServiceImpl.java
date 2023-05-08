package com.samplebank.account.service;

import com.samplebank.account.domain.ClientAccount;
import com.samplebank.account.domain.ClientAccountRepositoryWrapper;
import com.samplebank.account.dto.ClientAccountCriteria;
import com.samplebank.account.dto.ClientAccountDto;
import com.samplebank.account.mapper.ClientAccountMapper;
import com.samplebank.client.domain.ClientRepositoryWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ClientAccountServiceImpl implements ClientAccountService{

    private final ClientAccountRepositoryWrapper clientAccountRepositoryWrapper;
    private final ClientRepositoryWrapper clientRepository;
    private final ClientAccountMapper clientAccountMapper;

    public ClientAccountServiceImpl(ClientAccountRepositoryWrapper clientAccountRepositoryWrapper, ClientRepositoryWrapper clientRepository, ClientAccountMapper clientAccountMapper) {
        this.clientAccountRepositoryWrapper = clientAccountRepositoryWrapper;
        this.clientRepository = clientRepository;
        this.clientAccountMapper = clientAccountMapper;
    }

    @Transactional
    @Override
    public void createClientAccount(Long clientId) {
        var client = this.clientRepository.findClById(clientId);
        this.clientAccountRepositoryWrapper.saveClientAccount(client);
    }

    @Override
    public ClientAccountDto getAccountById(Long accountId) {
        return this.clientAccountMapper.fromEntity(this.clientAccountRepositoryWrapper.getAccountById(accountId));
    }

    @Override
    public List<ClientAccountDto> getClientAccounts(Long clientId, String status) {
        final var accountStatus = Objects.isNull(status) ? null : ClientAccount.AccountStatus.valueOf(status);
        return this.clientAccountMapper.fromEntity(this.clientAccountRepositoryWrapper.getClientAccounts(new ClientAccountCriteria(clientId, accountStatus)));
    }

    @Override
    public List<ClientAccountDto> getMyClientAccounts(Authentication authentication, String status) {
        var client = this.clientRepository.findLoggedInClient(authentication);
        return this.getClientAccounts(client.getId(), status);
    }
}
