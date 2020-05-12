package com.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import com.student.dao.user.UserDaoImpl;
import com.student.model.Response;
import com.student.model.UserInfo;
import com.student.security.SecurityConstants;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;



@RestController
@CrossOrigin
@RequestMapping("/users")
public class AuthController {

    @Autowired
    private UserDaoImpl userdao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


      // -------------------User Signin------------------------------------------
      @PostMapping("/authenticate")
      public ResponseEntity<?> userLogin(@Valid @RequestBody UserInfo user) {
          
          if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
              return new ResponseEntity<Response>(new Response(false, "Missing username and/or password.", null, null), HttpStatus.OK);
          }
          
          UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
          if (userDetails == null) {
              return new ResponseEntity<Response>(new Response(false, "Invalid username and/or password.", null, null), HttpStatus.OK);
          }
          
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), new ArrayList<>());
  
          authenticationManager.authenticate(usernamePasswordAuthenticationToken);
  
          if (!usernamePasswordAuthenticationToken.isAuthenticated()) { 
              return new ResponseEntity<Response>(new Response(false, "Invalid username and/or password.", null, null), HttpStatus.OK);
          }
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
  
          String token = Jwts.builder()
                  .setSubject(((org.springframework.security.core.userdetails.User) usernamePasswordAuthenticationToken.getPrincipal()).getUsername())
                  .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                  .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                  .compact();
          
          return new ResponseEntity<Response>(new Response(true, "Successfully Logged In.", token, userdao.findByUsername(user.getUsername()).getFullname()), HttpStatus.OK);
          
      }
  
  
      @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
      @ResponseBody
      public List<UserInfo> listAllUsers() {
          List<UserInfo> users = userdao.getUsers(null);
          return users;
      }
  
      // -------------------Retrieve Single User------------------------------------------
      
      @GetMapping(value = "/{id}")
      public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        UserInfo user = userdao.findById(id);
          if (user == null) {
            return new ResponseEntity<Response>(new Response(false, "User with id " + id + " not found.", null, null), HttpStatus.OK);
          }
          return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
      }
  
      // -------------------Create a User-------------------------------------------

      @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<?> createUser(@RequestBody UserInfo user, UriComponentsBuilder ucBuilder) {
          try {
            if (userdao.findByUsername(user.getUsername()) != null) {
                return new ResponseEntity<Response>(new Response(false, "Username "+user.getUsername()+" already exists.", null, null), HttpStatus.OK);
              }
              userdao.save(user);
              return new ResponseEntity<Response>(new Response(true, "Successfully Created User.", null, null), HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured "+e.getMessage(), null, null), HttpStatus.OK);
          }
      }
  
      // ------------------- Update a User ------------------------------------------------

      @PutMapping(value = "/{id}")
      public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserInfo user) {
  
          try {
            if (userdao.findById(id) == null) {
                return new ResponseEntity<Response>(new Response(false, "Username with id "+id+" not found.", null, null), HttpStatus.OK);
              }
              user.setId(id);
      
              userdao.save(user);
              return new ResponseEntity<Response>(new Response(true, "Successfully Updated User.", null, null), HttpStatus.OK);
          } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured "+e.getMessage(), null, null), HttpStatus.OK);
          }
      }
  
      // ------------------- Delete a User-----------------------------------------
     
      @DeleteMapping(value = "/{id}")
      public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
          try {
            UserInfo user = userdao.findById(id);
            if (user == null) {
              return new ResponseEntity<Response>(new Response(false, "Username with id "+id+" not found.", null, null), HttpStatus.OK);
            }
            userdao.delete(user);
            return new ResponseEntity<Response>(new Response(true, "Successfully Removed User.", null, null), HttpStatus.OK);
          } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured "+e.getMessage(), null, null), HttpStatus.OK);
          }
      }
  

}

