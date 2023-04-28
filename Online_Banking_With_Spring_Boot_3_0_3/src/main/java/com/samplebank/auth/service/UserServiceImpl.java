
package com.samplebank.auth.service;

import com.samplebank.auth.dto.ChangePasswordDto;
import com.samplebank.auth.dto.JwtRequest;
import com.samplebank.auth.dto.LoginResponse;
import com.samplebank.auth.dto.UserDto;
import com.samplebank.client.domain.Client;
import com.samplebank.auth.domain.User;
import com.samplebank.auth.exception.UserNotFoundException;
import com.samplebank.auth.mapper.UserMapper;
import com.samplebank.auth.domain.UserRepository;
import com.samplebank.security.CurrentUserDetails;
import com.samplebank.security.JwtTokenUtil;
import com.samplebank.utilities.GeneralConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author simiyu
 */
@Service
@RequiredArgsConstructor
@Validated
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUserDetails currentUserDetails;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public Optional<User> createUser(UserDto userDto, Client client) {
        return Optional.of(userRepository.save(User.createUser(userDto, client, passwordEncoder)));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findUserById(Long id) {
        return this.userMapper.fromEntity(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found By ID")));
    }

    @Transactional(readOnly = true)
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
          final var userDetails = currentUserDetails.loadUserByUsername(request.username());
          if (Objects.isNull(userDetails)) {
              createDefaultAdminUser();
              throw  new UserNotFoundException("Bad User Credentials !!");
          }else {
              final var user = findUserByUserName(userDetails.getUsername());
              this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails, request.password(), userDetails.getAuthorities()));
              return new LoginResponse(true, "Login Was Successful", JwtTokenUtil.createToken(user));
          }

    }


    private void createDefaultAdminUser(){
        if (this.userRepository.findAll().stream().filter(u -> GeneralConstants.ROLE_ADMIN.equals(u.getRole())).findFirst().isEmpty()) {
            UserDto userDto = new UserDto(GeneralConstants.DEFAULT_ADMIN_USER_NAME, GeneralConstants.DEFAULT_ADMIN_PASSWORD);
            this.createUser(userDto, null);
        }
    }
    
}
