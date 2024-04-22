package com.mrbonk97.springnextjsoauth2jwtserver.repository;

import com.mrbonk97.springnextjsoauth2jwtserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
