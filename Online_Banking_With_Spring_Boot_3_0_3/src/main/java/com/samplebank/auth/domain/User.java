package com.samplebank.auth.domain;

import com.samplebank.client.domain.Client;
import com.samplebank.auth.dto.UserDto;
import com.samplebank.shared.entity.BaseEntity;
import com.samplebank.utilities.GeneralConstants;
import jakarta.persistence.*;

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

    @OneToOne(fetch = FetchType.LAZY)
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
        return Objects.isNull(this.client) ? this.username : String.format("%s %s", this.client.getFirstName(), this.client.getLastName());
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if(!(obj instanceof User user)){
            return false;
        }
        return Objects.equals(this.getId(), user.getId()) && Objects.equals(this.getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        var hash = 17;
        hash = 31 * hash + this.getId().hashCode();
        hash = 31 * hash + this.getUsername().hashCode();
        return hash;
    }
}
