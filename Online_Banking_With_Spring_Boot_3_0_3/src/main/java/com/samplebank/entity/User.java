package com.samplebank.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;

@Entity
@Table(name = "user")
@Getter
public class User implements Serializable{

     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    
    @OneToOne(mappedBy = "user")
    private Client client;

    private User() {
    }
    
    
    
}
