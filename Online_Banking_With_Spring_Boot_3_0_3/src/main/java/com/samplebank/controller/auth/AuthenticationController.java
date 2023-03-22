package com.samplebank.controller.auth;

import com.samplebank.dto.JwtRequest;
import com.samplebank.dto.LoginResponse;
import com.samplebank.security.UserService;
import com.samplebank.utilities.GeneralConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping(value = GeneralConstants.AUTHENTICATION_ENDPOINT+"authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> createClient(@Valid @RequestBody JwtRequest jwtRequest){
        return new ResponseEntity<>(userService.authenticateUser(jwtRequest), HttpStatus.OK);
    }
}
