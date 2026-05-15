package com.cognizant.ems.repository;

import com.cognizant.ems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Used by Spring Security to find user by their login name
    Optional<User> findByUsername(String username);
}
