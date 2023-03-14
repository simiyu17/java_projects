package com.samplebank.controller;

import com.samplebank.dto.ClientDto;
import com.samplebank.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {
    
    private final ClientService clientService;

    @PostMapping("/clients")
    public ResponseEntity<String> createClient(ClientDto clientDto){
        clientService.createClient(clientDto);
        return new ResponseEntity<>("Client Successfully Created !!", HttpStatus.CREATED);
    }
}
