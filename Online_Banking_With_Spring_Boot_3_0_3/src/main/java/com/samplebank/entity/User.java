package com.samplebank.entity;

import com.samplebank.dto.UserDto;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
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
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType UserType;
    
    @Transient
    private String userFullName;
    
    public User(String username, String password, Client client) {
        this.username = username;
        this.password = password;
        this.client = client;
        this.UserType = Objects.nonNull(this.client) ? UserType.CLIENT_USER : UserType.ADMIN_USER;
    }
    
    
    

    public String getUserFullNameFromClient(){
        return String.format("%s %s", this.client.getFirstName(), this.client.getLastName());
    }
    
    private enum UserType{
        CLIENT_USER, ADMIN_USER;
    }
    
    public static User createUser(UserDto userDto, Client client, PasswordEncoder encoder){
        return new User(userDto.getUsername(), encoder.encode(userDto.getPassword()), client);
    }
    
    public void setUserClient(Client client){
        client.upDateUser(this);
    }
    
    
}
