package com.samplebank.account.api;

import com.samplebank.account.dto.ClientAccountDto;
import com.samplebank.account.service.ClientAccountService;
import com.samplebank.shared.dto.ApiResponse;
import com.samplebank.utilities.GeneralConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientAccountController {

    private final ClientAccountService clientAccountService;

    public ClientAccountController(ClientAccountService clientAccountService) {
        this.clientAccountService = clientAccountService;
    }

    @PostMapping(GeneralConstants.ADMIN_ENDPOINT+"create-account/{client-id}")
    public ResponseEntity<ApiResponse> createClientAccount(@PathVariable("client-id") Long clientId,  @RequestBody @Valid ClientAccountDto clientAccountDto){
        this.clientAccountService.createClientAccount(clientId, clientAccountDto);
        return new ResponseEntity<>(new ApiResponse(true, GeneralConstants.RESOURCE_SUCCESSFULLY_POSTED_MESSAGE), HttpStatus.CREATED);
    }

    @GetMapping(GeneralConstants.CLIENT_ENDPOINT+"my-accounts")
    public ResponseEntity<ClientAccountDto> getClientAccounts(Authentication authentication, @RequestParam("status") String status){

    }
}
