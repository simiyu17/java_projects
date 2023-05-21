package com.samplebank.client.service;

import com.samplebank.client.domain.ClientRepositoryWrapper;
import com.samplebank.client.dto.ClientDto;
import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
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


    private static final List<ClientDto> clients2 = Instancio.ofList(ClientDto.class).size(3).create();

    @Test
    void findClientById() {
        ClientDto mockedClient = Instancio.create(ClientDto.class);
        when(clientRepository.findClientById(anyLong())).thenReturn(mockedClient);
        final var clientDto = clientService.findClientById(1L);
        Assertions.assertThat(clientDto).isNotNull().isEqualTo(mockedClient);
    }


    @Test
    void getAvailableClients() {
        when(clientRepository.getAvailableClients()).thenReturn(clients2);
        final var clientDtos = clientService.getAvailableClients();
        Assertions.assertThat(clientDtos).isNotNull().hasSize(3).isInstanceOf(List.class).contains(clients2.get(0));
    }
}