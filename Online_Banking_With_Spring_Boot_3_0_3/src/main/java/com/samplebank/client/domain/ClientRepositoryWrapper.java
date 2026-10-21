package com.samplebank.client.domain;

import com.samplebank.auth.dto.UserDto;
import com.samplebank.client.dto.ClientDto;
import com.samplebank.client.exception.ClientNotFoundException;
import com.samplebank.client.mapper.ClientMapper;
import com.samplebank.auth.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class ClientRepositoryWrapper {

    private final ClientRepository clientRepository;
    private final UserService userService;
    private final ClientMapper clientMapper;

    public ClientRepositoryWrapper(ClientRepository clientRepository, UserService userService, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.userService = userService;
        this.clientMapper = clientMapper;
    }

    @Transactional
    public Client createClientWithAssociatedUser(ClientDto clientDto){
        var client = clientRepository.save(Client.createClient(clientDto));
        var user = userService.createUser(new UserDto(client.getEmailAddress(), client.getEmailAddress()), client);
        user.ifPresent(value -> value.setUserClient(client));
        return client;
    }

    public ClientDto findClientById(Long clientId) {
        return this.clientMapper.fromEntity(this.clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("No Client found with id : "+clientId)));
    }

    public Client findClById(Long clientId) {
        return this.clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("No Client found with id : "+clientId));
    }

    public ClientDto findClientByUser(Authentication authentication) {
        var user = userService.findUserByUserName(authentication.getName());
        if(Objects.isNull(user.getClient())){
            throw new ClientNotFoundException("No Client found with given authentication");
        }
        return this.clientMapper.fromEntity(user.getClient());
    }

    public Client findLoggedInClient(Authentication authentication) {
        var user = userService.findUserByUserName(authentication.getName());
        if(Objects.isNull(user.getClient())){
            throw new ClientNotFoundException("No Client found with given authentication");
        }
        return user.getClient();
    }

    public List<ClientDto> getAvailableClients() {
        return this.clientMapper.fromEntity(this.clientRepository.findAll());
    }

}
