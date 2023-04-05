package com.samplebank.service.client;

import com.samplebank.dto.ClientDto;
import com.samplebank.dto.UserDto;
import com.samplebank.entity.Client;
import com.samplebank.entity.User;
import com.samplebank.exceptions.ClientNotFoundException;
import com.samplebank.mapper.client.ClientMapper;
import com.samplebank.repository.ClientRepository;
import com.samplebank.security.UserService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
    
    private final ClientRepository clientRepository;
    private final UserService userService;
    private final ClientMapper clientMapper;

    @Transactional
    @Override
    public void createClient(ClientDto clientDto) {
        var client = clientRepository.save(Client.createClient(clientDto));
        var user = userService.createUser(new UserDto(client.getEmailAddress(), client.getEmailAddress()), client);
        user.setUserClient(client);
    }

    @Override public ClientDto findClientById(Long clientId) {
        return this.clientMapper.fromEntity(this.clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("No Client found with id : "+clientId)));
    }

    @Override public ClientDto findClientByUser(Authentication authentication) {
        var user = userService.findUserByUserName(authentication.getName());
        if(Objects.isNull(user) || Objects.isNull(user.getClient())){
            throw new ClientNotFoundException("No Client found with given authentication");
        }
        return this.clientMapper.fromEntity(user.getClient());
    }

    @Override public List<ClientDto> getAvailableClients() {
        return this.clientMapper.fromEntity(this.clientRepository.findAll());
    }

}
