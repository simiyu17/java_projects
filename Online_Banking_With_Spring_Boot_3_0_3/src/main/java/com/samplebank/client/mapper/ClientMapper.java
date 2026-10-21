
package com.samplebank.client.mapper;

import com.samplebank.client.dto.ClientDto;
import com.samplebank.client.domain.Client;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author simiyu
 */

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "gender", expression = "java(client.getGender().toString())")
    @Mapping(target = "clientStatus", expression = "java(client.getClientStatus().toString())")
    ClientDto fromEntity(Client client);
    List<ClientDto> fromEntity(List<Client> clients);
}
