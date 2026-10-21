package com.samplebank.client.domain;

import com.samplebank.client.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{ //}, JpaSpecificationExecutor<Client>, QuerydslPredicateExecutor<Client> {

}
