package com.example.brandboostserver.repo;

import com.example.brandboostserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findAllByUsernameContainingIgnoreCase(String username);
    User findByUsername(String username);
}
