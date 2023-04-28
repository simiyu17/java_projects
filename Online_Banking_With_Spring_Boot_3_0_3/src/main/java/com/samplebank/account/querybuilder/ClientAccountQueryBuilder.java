package com.samplebank.account.querybuilder;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.samplebank.account.domain.QClientAccount;
import com.samplebank.account.dto.ClientAccountCriteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ClientAccountQueryBuilder {

    public BooleanBuilder buildPredicate(ClientAccountCriteria clientAccountCriteria){

        QClientAccount qClientAccount = QClientAccount.clientAccount;
        BooleanBuilder builder = new BooleanBuilder();

        List<Predicate> predicateList = new ArrayList<>();

        if(Objects.nonNull(clientAccountCriteria.clientId())){
            BooleanExpression expression = qClientAccount.client.id.eq(clientAccountCriteria.clientId());
            predicateList.add(expression);
        }

        if(Objects.nonNull(clientAccountCriteria.accountStatus())){
            BooleanExpression expression = qClientAccount.accountStatus.eq(clientAccountCriteria.accountStatus());
            predicateList.add(expression);
        }

        if (!predicateList.isEmpty()) {
            builder.orAllOf(predicateList.toArray(new Predicate[0]));
        }

        return builder;
    }
}
