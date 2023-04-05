/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samplebank.mapper.client;

import com.samplebank.dto.ClientDto;
import com.samplebank.entity.Client;
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
