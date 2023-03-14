/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samplebank.dto;

import java.io.Serializable;
import lombok.Getter;

/**
 *
 * @author simiyu
 */
@Getter
public class UserDto implements Serializable{
    
    private Long id;

    private String username;

    private String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
    
}
