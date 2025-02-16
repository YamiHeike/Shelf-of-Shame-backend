package com.example.shelfofshame.mappers;

import com.example.shelfofshame.dto.SignupDto;
import com.example.shelfofshame.dto.UserDto;
import com.example.shelfofshame.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignupDto userDto);
}
