package com.samplebank.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;

@Entity
@Table(name = "client")
@Getter
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private LocalDate joinedDate;

    private LocalDate activationDate;

    private LocalDate closedDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;

    @Enumerated(EnumType.STRING)
    private ClientGovernmentIDType clientIDType;

    private String clientGovernmentId;

    private String cellPhone;

    @Email
    private String emailAddress;
    
    @Transient
    private String username;

    @Embedded
    private ClientAddress clientAddress;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Client(){}
    


    enum Gender{
        MALE, FEMALE
    }

    enum ClientStatus{
        ACTIVE, CLOSED
    }

    enum ClientGovernmentIDType{
        ID, PASSPORT
    }
}
