package com.samplebank.account.domain;

import com.samplebank.account.dto.ClientAccountDto;
import com.samplebank.client.domain.Client;
import com.samplebank.shared.entity.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Entity
@Table(name = "client_accounts",
        uniqueConstraints = {@UniqueConstraint(name = "UNIQUE_ACCOUNT_NUMBER", columnNames = "account_number"),
        @UniqueConstraint(name = "UNIQUE_ACCOUNT_TYPE", columnNames = {"client_id", "account_type"})})
public class ClientAccount extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(name = "account_number")
    private String accountNumber;

    private BigDecimal balance;

    @OneToMany(mappedBy = "clientAccount")
    private Set<AccountTransaction> accountTransactions;

    protected ClientAccount(){}

    private ClientAccount(Client client, AccountType type, String accountNumber){
        this.client = client;
        this.accountType = type;
        this.accountStatus = AccountStatus.ACTIVE;
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
    }

    public void updateClient(Client client){
        this.client = client;
    }

    public enum AccountType {
        SAVING_ACCOUNT("Savings Account"),
        CHECKING_ACCOUNT("Checking Account");

        private final String name;

        AccountType(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public enum AccountStatus {
        ACTIVE, INACTIVE;
    }

    public static ClientAccount createClientAccount(Client client, ClientAccountDto clientAccountDto){
        return new ClientAccount(client, AccountType.valueOf(clientAccountDto.accountType()), clientAccountDto.accountNumber());
    }

    public void addTransaction(AccountTransaction transaction){
        this.accountTransactions.add(transaction);
        transaction.setClientAccount(this);
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClientAccount clientAccount)){
            return false;
        }
        return new EqualsBuilder()
                .append(this, clientAccount)
                .append(getId(), clientAccount.getId())
                .append(getAccountType(), clientAccount.getAccountType())
                .append(this.getAccountNumber(), clientAccount.getAccountNumber())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17,31)
                .append(getId())
                .append(getAccountType())
                .append(getAccountNumber())
                .toHashCode();
    }
}
