package com.samplebank.account.domain;

import com.samplebank.shared.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "account_transaction")
@Getter
public class AccountTransaction extends BaseEntity implements Comparable<AccountTransaction>{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private ClientAccount clientAccount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String description;

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

    public void setClientAccount(ClientAccount account){
        this.clientAccount = account;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AccountTransaction accountTransaction)){
            return false;
        }
        return new EqualsBuilder()
                .append(this, accountTransaction)
                .append(getId(), accountTransaction.getId())
                .append(getAmount(), accountTransaction.getAmount())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(getId())
                .append(getAmount())
                .toHashCode();
    }

    @Override
    public int compareTo(AccountTransaction o) {
        return o.getDateCreated().compareTo(this.getDateCreated());
    }
}
