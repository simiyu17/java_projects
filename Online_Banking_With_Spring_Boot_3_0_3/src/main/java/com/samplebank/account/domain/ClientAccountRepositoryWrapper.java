package com.samplebank.account.domain;

import com.samplebank.account.dto.ClientAccountCriteria;
import com.samplebank.account.dto.ClientAccountDto;
import com.samplebank.account.exception.AccountNotFoundException;
import com.samplebank.account.querybuilder.ClientAccountQueryBuilder;
import com.samplebank.client.domain.Client;
import com.samplebank.config.ApplicationConfig;
import com.samplebank.utilities.AccountUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class ClientAccountRepositoryWrapper {

    private final ClientAccountRepository clientAccountRepository;
    private final ClientAccountQueryBuilder queryBuilder;
    private final ApplicationConfig applicationConfig;


    public ClientAccountRepositoryWrapper(ClientAccountRepository clientAccountRepository, ClientAccountQueryBuilder queryBuilder, ApplicationConfig applicationConfig) {
        this.clientAccountRepository = clientAccountRepository;
        this.queryBuilder = queryBuilder;
        this.applicationConfig = applicationConfig;
    }

    @Transactional
    public void saveClientAccount(Client client){
        final var clientAccountNumber = AccountUtil.generateClientAccountNumber(client.getId(), applicationConfig.getClientAccountLength());
        var clientAccount = ClientAccount.createClientAccount(client, clientAccountNumber);
        this.clientAccountRepository.save(clientAccount);
    }

    public ClientAccount getAccountById(Long accountId) {
        return clientAccountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(String.format("Account with Id %d Not Found !!", accountId)));
    }

    public List<ClientAccount> getClientAccounts(ClientAccountCriteria criteria) {
        return (List<ClientAccount>) this.clientAccountRepository.findAll(this.queryBuilder.buildPredicate(criteria));
    }
}
