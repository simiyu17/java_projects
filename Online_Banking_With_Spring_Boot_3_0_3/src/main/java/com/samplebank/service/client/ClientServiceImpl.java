package com.samplebank.service.client;

import com.samplebank.dto.ClientDto;
import com.samplebank.dto.UserDto;
import com.samplebank.entity.Client;
import com.samplebank.repository.ClientRepository;
import com.samplebank.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
    
    private final ClientRepository clientRepository;
    private final UserService userService;

    @Transactional
    @Override
    public void createClient(ClientDto clientDto) {
        var client = clientRepository.save(Client.createClient(clientDto));
        var user = userService.createUser(new UserDto(client.getEmailAddress(), client.getEmailAddress()), client);
        user.setUserClient(client);
    }

    @Override public ClientDto findClientById(Long clientId) {
        return null;
    }

    @Override public Page<ClientDto> getAvailableClients() {
        return null;
    }

}
