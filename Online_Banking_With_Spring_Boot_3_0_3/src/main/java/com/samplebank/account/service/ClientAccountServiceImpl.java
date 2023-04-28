package com.samplebank.account.service;

import com.samplebank.account.domain.ClientAccount;
import com.samplebank.account.domain.ClientAccountRepositoryWrapper;
import com.samplebank.account.dto.ClientAccountCriteria;
import com.samplebank.account.dto.ClientAccountDto;
import com.samplebank.account.mapper.ClientAccountMapper;
import com.samplebank.client.domain.ClientRepositoryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void createClientAccount(Long clientId, ClientAccountDto clientAccountDto) {
        var client = this.clientRepository.findClById(clientId);
        this.clientAccountRepositoryWrapper.saveClientAccount(client, clientAccountDto);
    }

    @Override
    public ClientAccountDto getAccountById(Long accountId) {
        return this.clientAccountMapper.fromEntity(this.clientAccountRepositoryWrapper.getAccountById(accountId));
    }

    @Override
    public List<ClientAccountDto> getClientAccounts(Long clientId, String status) {
        return this.clientAccountMapper.fromEntity(this.clientAccountRepositoryWrapper.getClientAccounts(new ClientAccountCriteria(clientId, ClientAccount.AccountStatus.valueOf(status))));
    }
}
