/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.samplebank.security;

import com.samplebank.dto.ChangePasswordDto;
import com.samplebank.dto.JwtRequest;
import com.samplebank.dto.LoginResponse;
import com.samplebank.dto.UserDto;
import com.samplebank.entity.Client;
import com.samplebank.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author simiyu
 */
public interface UserService {
    
    User createUser(UserDto userDto, Client client);

    UserDto findUserById(Long id);

    List<UserDto> findAvailableUsers();
    
    void changeUserPassword(@NotNull Long id, @NotBlank ChangePasswordDto changePasswordDto);
    
    User findUserByUserName(String userName);
    
    LoginResponse authenticateUser(@NotNull JwtRequest request);
}
