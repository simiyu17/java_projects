/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.samplebank.security;

import com.samplebank.dto.JwtRequest;
import com.samplebank.dto.LoginResponse;
import com.samplebank.entity.User;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author simiyu
 */
public interface UserService {
    
    User createUser(User user);
    
    User findUserById(Long id);
    
    User updateUser(User user);
    
    User findUserByUserName(String userName);
    
    LoginResponse authenticateUser(@NotNull JwtRequest request);
}
