/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.samplebank.auth.service;

import com.samplebank.auth.dto.ChangePasswordDto;
import com.samplebank.auth.dto.JwtRequest;
import com.samplebank.auth.dto.LoginResponse;
import com.samplebank.auth.dto.UserDto;
import com.samplebank.client.domain.Client;
import com.samplebank.auth.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author simiyu
 */
public interface UserService {
    
    Optional<User> createUser(UserDto userDto, Client client);

    UserDto findUserById(Long id);

    List<UserDto> findAvailableUsers();
    
    void changeUserPassword(@NotNull Long id, @NotBlank ChangePasswordDto changePasswordDto);
    
    User findUserByUserName(String userName);
    
    LoginResponse authenticateUser(@NotNull JwtRequest request);
}
