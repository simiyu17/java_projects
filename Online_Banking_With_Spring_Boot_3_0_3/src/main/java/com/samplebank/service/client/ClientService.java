package com.samplebank.service.client;

import com.samplebank.dto.ClientDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

public interface ClientService {

    void createClient(ClientDto clientDto);

    ClientDto findClientById(@NotNull Long clientId);

    Page<ClientDto> getAvailableClients();

}
