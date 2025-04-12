package com.example.shelfofshame.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserProvider {
    private final UserService userService;

    public User getCurrentUser(Principal principal) {
        return userService.findUserByEmail(principal.getName());
    }
}
