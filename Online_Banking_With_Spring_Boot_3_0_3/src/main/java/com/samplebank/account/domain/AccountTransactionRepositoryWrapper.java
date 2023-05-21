package com.samplebank.account.domain;

import com.samplebank.account.dto.TransactionCriteria;
import com.samplebank.account.exception.AccountTransactionNotFoundException;
import com.samplebank.account.querybuilder.AccountTransactionQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionRepositoryWrapper {

    private final AccountTransactionRepository accountTransactionRepository;
    private final AccountTransactionQueryBuilder accountTransactionQueryBuilder;

    public AccountTransactionRepositoryWrapper(AccountTransactionRepository accountTransactionRepository, AccountTransactionQueryBuilder accountTransactionQueryBuilder) {
        this.accountTransactionRepository = accountTransactionRepository;
        this.accountTransactionQueryBuilder = accountTransactionQueryBuilder;
    }

    public AccountTransaction findTransactionById(Long transactionId){
        return this.accountTransactionRepository.findById(transactionId).orElseThrow(() -> new AccountTransactionNotFoundException(transactionId));
    }

    public Page<AccountTransaction> getAccountTransactions(TransactionCriteria criteria, Pageable pageable){
        return this.accountTransactionRepository.findAll(accountTransactionQueryBuilder.buildAccountTransactionQueryBuilder(criteria), pageable);
    }
}
