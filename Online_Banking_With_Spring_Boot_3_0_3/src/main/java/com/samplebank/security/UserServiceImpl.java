/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samplebank.security;

import com.samplebank.dto.ChangePasswordDto;
import com.samplebank.dto.JwtRequest;
import com.samplebank.dto.LoginResponse;
import com.samplebank.dto.UserDto;
import com.samplebank.entity.Client;
import com.samplebank.entity.User;
import com.samplebank.exceptions.UserNotFoundException;
import com.samplebank.mapper.user.UserMapper;
import com.samplebank.repository.UserRepository;
import com.samplebank.utilities.GeneralConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author simiyu
 */
@Service
@RequiredArgsConstructor
@Validated
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUserDetails currentUserDetails;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public User createUser(UserDto userDto, Client client) {
        return userRepository.save(User.createUser(userDto, client, passwordEncoder));
    }

    @Override
    public UserDto findUserById(Long id) {
        return this.userMapper.fromEntity(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found By ID")));
    }

    @Override public List<UserDto> findAvailableUsers() {
        return this.userMapper.fromEntity(userRepository.findAll());
    }

    @Transactional
    @Override public void changeUserPassword(Long id, ChangePasswordDto changePasswordDto) {
        var user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found By ID"));
        if (this.passwordEncoder.matches(changePasswordDto.newPassword(), user.getPassword())){
            user.updatePassWord(this.passwordEncoder.encode(changePasswordDto.newPassword()));
        }else {
            throw new UserNotFoundException("Bad Credentials!!");
        }
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() -> new UserNotFoundException("User Not Found By User Name"));
    }



    @Override
    public LoginResponse authenticateUser(final JwtRequest request) {
          final UserDetails userDetails = currentUserDetails.loadUserByUsername(request.username());
          if (Objects.isNull(userDetails)) {
              createDefaultAdminUser();
          }
          final var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, request.password(), Objects.isNull(userDetails) ? List.of() : userDetails.getAuthorities());
          authenticationManager.authenticate(usernamePasswordAuthenticationToken);
          final var user = findUserByUserName(request.username());
          return new LoginResponse(true, "Login Was Successful", JwtTokenUtil.createToken(user));
    }


    private void createDefaultAdminUser(){
        if (this.userRepository.findAll().stream().filter(u -> u.getRole().equals(GeneralConstants.ROLE_ADMIN)).findFirst().isEmpty()) {
            UserDto userDto = new UserDto(GeneralConstants.DEFAULT_ADMIN_USER_NAME, GeneralConstants.DEFAULT_ADMIN_PASSWORD);
            this.createUser(userDto, null);
        }
    }
    
}
