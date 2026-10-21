
package com.samplebank.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author simiyu
 */

public record ClientDto (
    
    Long id,

    @NotNull
    String firstName,

    @NotNull
    String lastName,

    LocalDate joinedDate,

    LocalDate activationDate,

    LocalDate closedDate,

    @NotNull
    String gender,

    String clientStatus,

    @NotNull
    String clientGovernmentId,

    String cellPhone,

    @NotNull
    @Email
    String emailAddress
)implements Serializable{

    public ClientDto(String firstName, String lastName, String gender, String clientGovernmentId, String cellPhone, String emailAddress) {
        this(null, firstName, lastName, null, null, null, gender, null,clientGovernmentId, cellPhone, emailAddress);
    }
    
    
}
