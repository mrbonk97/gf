package com.mrbonk97.springnextjsoauth2jwtserver.service;

import com.mrbonk97.springnextjsoauth2jwtserver.model.User;
import com.mrbonk97.springnextjsoauth2jwtserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User loadByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Cannot find Account"));
    }

    public User loadById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Cannot find Account"));
    }

    public User signUp(String email, String name, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User signIn(String email, String password) {
        User user = loadByEmail(email);
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Password does not match");
        }
        return user;
    }

}
