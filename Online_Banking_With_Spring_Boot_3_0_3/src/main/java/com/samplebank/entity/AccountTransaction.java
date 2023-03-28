package com.samplebank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;

@Entity
@Table(name = "account_transaction")
@Getter
public class AccountTransaction extends BaseEntity implements Comparable<AccountTransaction>{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private ClientAccount clientAccount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal amount;

    public enum TransactionType{
        DEPOSIT("Deposit"),
        WITHDRAW("Withdraw"),
        TRANSFER_IN("Transfer In"),
        TRANSFER_OUT("Transfer Out");

        private final String name;

        TransactionType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Override
    public int compareTo(AccountTransaction o) {
        return o.getDateCreated().compareTo(this.getDateCreated());
    }
}
