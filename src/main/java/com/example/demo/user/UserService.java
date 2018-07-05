package com.example.demo.user;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserObject user = userRepository.findUserObjectByUsername(username);

        if (user != null) {
            return User.builder()
                    .roles(user.getRoles())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();
        }

        throw new UsernameNotFoundException(String.format("UserObject %s cannot be found", username));
    }

    public List<String> getPlayers() {
        return userRepository.findUserObjectsByRolesContains("PLAYER").stream()
                .map(UserObject::getUsername)
                .collect(Collectors.toList());
    }
}
