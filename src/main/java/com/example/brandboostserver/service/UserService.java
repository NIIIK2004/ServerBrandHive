package com.example.brandboostserver.service;


import com.example.brandboostserver.model.User;
import com.example.brandboostserver.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void save(User user) throws IOException {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setRoles(user.getRoles());
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> getById(Long id){
        return userRepo.findById(id);
    }

    public void delete(Long id){
        this.userRepo.deleteById(id);
    }

    public List<User> findByUserByOption(String username){
        return userRepo.findAllByUsernameContainingIgnoreCase(username);
    }

    public User findUserByUsername(String username){
        return userRepo.findByUsername(username);
    }
}
