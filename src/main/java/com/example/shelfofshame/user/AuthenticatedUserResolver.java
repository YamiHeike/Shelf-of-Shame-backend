package com.example.shelfofshame.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserResolver {
    private final UserService userService;

    public User getUser(Principal principal) {
        return userService.findUserByEmail(principal.getName());
    }
}
