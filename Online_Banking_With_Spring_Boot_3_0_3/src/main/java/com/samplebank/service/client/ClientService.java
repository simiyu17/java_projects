package com.samplebank.service.client;

import com.samplebank.dto.ClientDto;
import com.samplebank.entity.User;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.security.core.Authentication;

public interface ClientService {

    void createClient(ClientDto clientDto);

    ClientDto findClientById(@NotNull Long clientId);

    ClientDto findClientByUser(@NotNull Authentication authentication);

    List<ClientDto> getAvailableClients();

}
