package com.samplebank.controller.client;

import com.samplebank.dto.ClientDto;
import com.samplebank.service.client.ClientService;
import com.samplebank.utilities.GeneralConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(GeneralConstants.CLIENT_ENDPOINT+"balance")
    public ResponseEntity<String> getBalance(){
        return new ResponseEntity<>("Your current balance is KES 20, 000", HttpStatus.CREATED);
    }
}
