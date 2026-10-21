package com.samplebank.auth.api;

import com.samplebank.auth.dto.ChangePasswordDto;
import com.samplebank.auth.dto.UserDto;
import com.samplebank.auth.service.UserService;
import com.samplebank.shared.annotations.RestControllerWithOpenAPI;
import com.samplebank.utilities.GeneralConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestControllerWithOpenAPI
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping(GeneralConstants.AUTHENTICATION_ENDPOINT+"change-password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordDto changePasswordDto){
        userService.changeUserPassword(id, changePasswordDto);
        return new ResponseEntity<>("Password successfully updated !!", HttpStatus.OK);
    }

    @GetMapping(GeneralConstants.ADMIN_ENDPOINT+"users")
    public ResponseEntity<List<UserDto>> getAvailableUsers(){
        return new ResponseEntity<>(userService.findAvailableUsers(), HttpStatus.OK);
    }
}
