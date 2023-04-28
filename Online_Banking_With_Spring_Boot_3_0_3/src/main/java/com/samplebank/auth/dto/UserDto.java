
package com.samplebank.auth.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author simiyu
 */
@Setter
public class UserDto implements Serializable{

    private static final String PASSWORD_HIDDEN = "<[Protected]>";
    
    private Long id;

    private String name;

    private String username;

    @JsonIgnore
    private String password;

    private Set<String> roles;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHidden(){
        return PASSWORD_HIDDEN;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
