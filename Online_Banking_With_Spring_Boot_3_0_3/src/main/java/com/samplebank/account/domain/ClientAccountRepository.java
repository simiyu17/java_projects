package com.samplebank.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccount, Long>, JpaSpecificationExecutor<ClientAccount>, QuerydslPredicateExecutor<ClientAccount> {
}
