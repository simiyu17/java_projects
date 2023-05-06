package com.samplebank.client.service;

import com.samplebank.client.domain.ClientRepositoryWrapper;
import com.samplebank.client.dto.ClientDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepositoryWrapper clientRepository;
    @InjectMocks
    private ClientServiceImpl clientService;

    private static final List<ClientDto> clients = List.of(new ClientDto("John", "Doe", "MALE", "5463756427", "254753645372", "john.doe@gmail.com"),
            new ClientDto("Jane", "Doe", "FEMALE", "9763756427", "254753645849", "jane.doe@gmail.com"),
            new ClientDto("David", "Doe", "MALE", "1113756427", "254752345372", "dave.doe@gmail.com"));


    @Test
    void findClientById() {
        when(clientRepository.findClientById(anyLong())).thenReturn(clients.get(0));
        final var clientDto = clientService.findClientById(1L);
        Assertions.assertThat(clientDto).isNotNull().isEqualTo(clients.get(0));
    }


    @Test
    void getAvailableClients() {
        when(clientRepository.getAvailableClients()).thenReturn(clients);
        final var clientDtos = clientService.getAvailableClients();
        Assertions.assertThat(clientDtos).isNotNull().hasSize(3).isInstanceOf(List.class).contains(clients.get(0));
    }
}