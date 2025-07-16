package com.example.shelfofshame.user;

import com.example.shelfofshame.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserProvider {
    private final UserService userService;

    public User getCurrentUser(Principal principal) {
        String email = principal.getName();
        if(principal instanceof Authentication) {
            Object principalObject = ((Authentication) principal).getPrincipal();
            if(principalObject instanceof UserDto) {
                email = ((UserDto) principalObject).getEmail();
            }
        }
        return userService.findUserByEmail(email);
    }
}
