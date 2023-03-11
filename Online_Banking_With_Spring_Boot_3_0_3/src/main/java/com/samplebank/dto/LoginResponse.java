/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samplebank.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author simiyu
 */
@Getter
@AllArgsConstructor
public class LoginResponse implements Serializable {
    
    private final boolean success;

    private final String msg;
    
    private final String authToken;
        
}
