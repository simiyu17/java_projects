/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samplebank.dto;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author simiyu
 */
public record JwtRequest(@NotBlank String username, @NotBlank String password) implements Serializable {

}
