package com.samplebank.entity;

import com.samplebank.dto.UserDto;
import com.samplebank.utilities.GeneralConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }, name = "username_UNIQUE")})
@Getter @NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "user_role")
    private String role;

    @Transient
    private String userFullName;

    public User(String username, String password, Client client) {
        this.username = username;
        this.password = password;
        this.client = client;
        this.role = Objects.nonNull(this.client) ? GeneralConstants.ROLE_CLIENT : GeneralConstants.ROLE_ADMIN;
    }




    public String getUserFullNameFromClient(){
        if (Objects.isNull(this.client)){
            return this.username;
        }
        return String.format("%s %s", this.client.getFirstName(), this.client.getLastName());
    }

    public static User createUser(UserDto userDto, Client client, PasswordEncoder encoder){
        return new User(userDto.getUsername(), encoder.encode(userDto.getPassword()), client);
    }

    public void setUserClient(Client client){
        client.upDateUser(this);
    }

    public void updatePassWord(String newEncodedPassword){
        this.password = newEncodedPassword;
    }

}
