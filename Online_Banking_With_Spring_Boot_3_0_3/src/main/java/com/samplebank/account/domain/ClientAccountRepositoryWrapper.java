package com.samplebank.account.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.samplebank.account.dto.ClientAccountCriteria;
import com.samplebank.account.dto.ClientAccountDto;
import com.samplebank.account.exception.AccountNotFoundException;
import com.samplebank.account.querybuilder.ClientAccountQueryBuilder;
import com.samplebank.client.domain.Client;
import com.samplebank.utilities.AccountUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class ClientAccountRepositoryWrapper {

    private final ClientAccountRepository clientAccountRepository;
    private final ClientAccountQueryBuilder queryBuilder;

    public ClientAccountRepositoryWrapper(ClientAccountRepository clientAccountRepository, ClientAccountQueryBuilder queryBuilder) {
        this.clientAccountRepository = clientAccountRepository;
        this.queryBuilder = queryBuilder;
    }

    @Transactional
    public void saveClientAccount(Client client, ClientAccountDto clientAccountDto){
        var clientAccount = ClientAccount.createClientAccount(client, clientAccountDto);
        clientAccount.setAccountNumber(AccountUtil.generateClientAccountNumber());
        this.clientAccountRepository.save(clientAccount);
    }

    public ClientAccount getAccountById(Long accountId) {
        return clientAccountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(String.format("Account with Id %d Not Found !!", accountId)));
    }

    public List<ClientAccount> getClientAccounts(ClientAccountCriteria criteria) {
        return (List<ClientAccount>) this.clientAccountRepository.findAll(this.queryBuilder.buildPredicate(criteria));
    }
}
