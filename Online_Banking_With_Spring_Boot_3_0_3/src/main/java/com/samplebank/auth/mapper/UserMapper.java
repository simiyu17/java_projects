
package com.samplebank.auth.mapper;

import com.samplebank.auth.dto.UserDto;
import com.samplebank.auth.domain.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author simiyu
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(java.util.Set.of(user.getRole()))")
    @Mapping(target = "name", expression = "java(user.getUserFullNameFromClient())")
    UserDto fromEntity(User user);
    List<UserDto> fromEntity(List<User> users);
}
