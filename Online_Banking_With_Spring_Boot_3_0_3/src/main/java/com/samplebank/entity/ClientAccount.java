package com.samplebank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;

@Getter
@Entity
@Table(name = "client_accounts")
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

    private String accountNumber;

    private BigDecimal balance;

    @OneToMany(mappedBy = "clientAccount")
    private Set<AccountTransaction> accountTransactions;

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

}
