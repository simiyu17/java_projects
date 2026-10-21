package com.samplebank.auth.api;

import com.samplebank.auth.dto.JwtRequest;
import com.samplebank.auth.dto.LoginResponse;
import com.samplebank.auth.service.UserService;
import com.samplebank.utilities.GeneralConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final UserService userService;

    @PostMapping(value = GeneralConstants.AUTHENTICATION_ENDPOINT+"authenticate")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody JwtRequest jwtRequest){
        return new ResponseEntity<>(userService.authenticateUser(jwtRequest), HttpStatus.OK);
    }
}
