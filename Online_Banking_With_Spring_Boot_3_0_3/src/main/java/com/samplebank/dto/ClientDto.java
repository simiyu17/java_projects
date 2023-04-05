/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samplebank.dto;

import com.samplebank.entity.Client;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author simiyu
 */
@Getter
@Setter
public class ClientDto implements Serializable{
    
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate joinedDate;

    private LocalDate activationDate;

    private LocalDate closedDate;

    private String gender;

    private String clientStatus;

    private String clientGovernmentId;

    private String cellPhone;

    private String emailAddress;

    public ClientDto(String firstName, String lastName, String gender, String clientGovernmentId, String cellPhone, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.clientGovernmentId = clientGovernmentId;
        this.cellPhone = cellPhone;
        this.emailAddress = emailAddress;
    }
    
    
}
