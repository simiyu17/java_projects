package com.samplebank.account.querybuilder;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.samplebank.account.domain.QAccountTransaction;
import com.samplebank.account.dto.TransactionCriteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AccountTransactionQueryBuilder {

    public BooleanBuilder buildAccountTransactionQueryBuilder(TransactionCriteria criteria){
        BooleanBuilder builder = new BooleanBuilder();
        List<Predicate> predicates = new ArrayList<>();
        QAccountTransaction qAccountTransaction = QAccountTransaction.accountTransaction;

        if (Objects.nonNull(criteria.fromDate()) && Objects.nonNull(criteria.toDate())){
            BooleanExpression expression = qAccountTransaction.dateCreated.between(criteria.fromDate(), criteria.toDate());
            predicates.add(expression);
        }
        if (Objects.nonNull(criteria.clientAccountId())){
            predicates.add(qAccountTransaction.clientAccount.id.eq(criteria.clientAccountId()));
        }
        if (Objects.nonNull(criteria.transactionType())){
            predicates.add(qAccountTransaction.transactionType.eq(criteria.transactionType()));
        }

        if (!predicates.isEmpty()){
            builder.orAllOf(predicates.toArray(new Predicate[0]));
        }

        return builder;
    }
}
