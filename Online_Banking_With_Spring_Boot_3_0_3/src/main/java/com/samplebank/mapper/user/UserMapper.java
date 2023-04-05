/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samplebank.mapper.user;

import com.samplebank.dto.UserDto;
import com.samplebank.entity.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author simiyu
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(java.util.Set.of(user.getRole()))")
    @Mapping(target = "password", expression = "java(user.getPassword())")
    UserDto fromEntity(User user);
    List<UserDto> fromEntity(List<User> users);
}
