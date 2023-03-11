/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samplebank.security;

import com.samplebank.dto.JwtRequest;
import com.samplebank.dto.LoginResponse;
import com.samplebank.entity.User;
import com.samplebank.exceptions.UserNotFoundException;
import com.samplebank.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author simiyu
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found By ID"));
    }

    @Override
    public User updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new UserNotFoundException("User Not Found By User Name"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElse(null);
        
        if(Objects.isNull(user)){
            return null;
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public LoginResponse authenticateUser(JwtRequest request) {
        if (request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
              return new LoginResponse(false, "Missing username and/or password.", null);
          }
          
          UserDetails userDetails = loadUserByUsername(request.getUsername());
          if (Objects.isNull(userDetails)) {
              return new LoginResponse(false, "Invalid username and/or password.", null);
          }
          
          var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, request.getPassword(), new ArrayList<>());
  
          authenticationManager.authenticate(usernamePasswordAuthenticationToken);
  
          if (!usernamePasswordAuthenticationToken.isAuthenticated()) {
              return new LoginResponse(false, "Invalid username and/or password.", null);
          }
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          
          var user = findUserByUserName(userDetails.getUsername());
  
          var token = Jwts.builder()
                  .setSubject(((org.springframework.security.core.userdetails.User) usernamePasswordAuthenticationToken.getPrincipal()).getUsername())
                  .claim("user_id", user.getId())
                  .claim("user_full_name", user.getUserFullNameFromClient())
                  .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                  .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                  .compact();
          
          return new LoginResponse(true, "Login Was Successful", token);
    }
    
}
