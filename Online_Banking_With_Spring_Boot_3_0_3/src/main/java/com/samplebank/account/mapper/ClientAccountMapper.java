package com.samplebank.account.mapper;

import com.samplebank.account.domain.ClientAccount;
import com.samplebank.account.dto.ClientAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientAccountMapper {

    @Mapping(target = "accountType", expression = "java(account.getAccountType().getName())")
    @Mapping(target = "accountStatus", expression = "java(String.valueOf(account.getAccountStatus()))")
    ClientAccountDto fromEntity(ClientAccount account);

    List<ClientAccountDto> fromEntity(List<ClientAccount> accounts);
}
