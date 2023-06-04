package com.samplebank.client.api;

import com.samplebank.client.dto.ClientDto;
import com.samplebank.client.service.ClientService;
import com.samplebank.shared.annotations.RestControllerWithOpenAPI;
import com.samplebank.utilities.GeneralConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestControllerWithOpenAPI
@RequiredArgsConstructor
public class ClientController {
    
    private final ClientService clientService;

    @PostMapping(GeneralConstants.ADMIN_ENDPOINT+"clients")
    public ResponseEntity<String> createClient(@Valid @RequestBody ClientDto clientDto){
        clientService.createClient(clientDto);
        return new ResponseEntity<>("Client Successfully Created !!", HttpStatus.CREATED);
    }

    @GetMapping(GeneralConstants.ADMIN_ENDPOINT+"clients/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("id") Long id){
        return new ResponseEntity<>(clientService.findClientById(id), HttpStatus.OK);
    }

    @GetMapping(GeneralConstants.CLIENT_ENDPOINT+"my-client-profile")
    public ResponseEntity<ClientDto> getMyProfile(Authentication authentication){
        return new ResponseEntity<>(clientService.findClientByUser(authentication), HttpStatus.OK);
    }

    @GetMapping(GeneralConstants.ADMIN_ENDPOINT+"clients")
    public ResponseEntity<List<ClientDto>> getClients(){
        return new ResponseEntity<>(clientService.getAvailableClients(), HttpStatus.OK);
    }
}
