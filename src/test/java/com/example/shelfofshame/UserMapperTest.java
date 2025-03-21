package com.example.shelfofshame;

import com.example.shelfofshame.user.User;
import com.example.shelfofshame.user.UserMapper;
import com.example.shelfofshame.user.UserRole;
import com.example.shelfofshame.user.dto.SignupDto;
import com.example.shelfofshame.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertNull;


public class UserMapperTest {
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void shouldMapToUserDto() {
        User user = User.builder()
                .id(31L)
                .email("test@example.com")
                .role(UserRole.USER)
                .password("password")
                .username("test")
                .build();
        UserDto userDto = mapper.toUserDto(user);
        assertNotNull(userDto);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getUsername(), user.getUsername());
    }

    @Test
    public void shouldMapToUser() {
        SignupDto signupDto = SignupDto.builder()
                .email("test@example.com")
                .username("test")
                .password("password")
                .build();
        User user = mapper.signUpToUser(signupDto);
        assertNotNull(user);
        assertEquals(user.getEmail(), signupDto.getEmail());
        assertEquals(user.getUsername(), signupDto.getUsername());
        assertNull(user.getPassword(), null);
    }
}
