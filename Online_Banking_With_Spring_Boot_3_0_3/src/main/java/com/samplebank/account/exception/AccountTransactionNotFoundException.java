package com.samplebank.account.exception;

public class AccountTransactionNotFoundException extends RuntimeException{
    public AccountTransactionNotFoundException(Long transactionId) {
        super(String.format("No Transaction found with id:  %d", transactionId));
    }
}
