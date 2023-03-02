/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.samplebank.service;

import com.samplebank.entity.User;

/**
 *
 * @author simiyu
 */
public interface UserService {
    
    User createUser(User user);
    
    User findUserById(Long id);
    
    User updateUser(User user);
    
    User findUserByUserName(String userName);
}
