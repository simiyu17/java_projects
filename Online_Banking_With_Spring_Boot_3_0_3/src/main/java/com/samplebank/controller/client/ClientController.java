package com.samplebank.controller.client;

import com.samplebank.dto.ClientDto;
import com.samplebank.entity.User;
import com.samplebank.service.client.ClientService;
import com.samplebank.utilities.GeneralConstants;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {
    
    private final ClientService clientService;

    @PostMapping(GeneralConstants.ADMIN_ENDPOINT+"clients")
    public ResponseEntity<String> createClient(@RequestBody ClientDto clientDto){
        clientService.createClient(clientDto);
        return new ResponseEntity<>("Client Successfully Created !!", HttpStatus.CREATED);
    }

    @GetMapping(GeneralConstants.ADMIN_ENDPOINT+"client/{id}")
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
